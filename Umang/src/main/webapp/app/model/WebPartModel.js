
Ext.define('ui.model.WebPartModel', {
    extend: 'Ext.data.Model'
    , alias: 'data.webpartmodel'
    , requires: ['ui.proxy.WebPartProxy']
    , postIdEvenPhantom: false
    , read: function (options) {
        return this.load(options);
    }
    , constructor: function (data, session) {
        this.callParent(arguments);
        // very important
        this.getProxy().reader.readAssociated(this, this.data);
    }
    , getData: function (options) {
        options = options || {};

        var data, fields;

        if (options.idOnly == true) {
            data = {};
            data[this.idProperty] = this.get(this.idProperty);
        }
        else {
            data = this.callParent(arguments);

            if (Ext.isArray(fields = options.fields)) {
                for (var prop in data) {
                    if (fields.indexOf(prop) == -1) {
                        delete data[prop];
                    }
                }
            }

            if (this.phantom == true && this.postIdEvenPhantom !== true) {
                delete data[this.idProperty];
            }
        }

        return data;
    }
    , getAssociatedData: function (result, options) {
        var me = this,
            associations = me.associations,
            deep, i, item, items, itemData, length, record, role, roleName, foreignKey, defaultOpts, opts, clear, associated;

        result = result || {};
        me.$gathering = 1;

        if (options) {
            options = Ext.Object.chain(options);
        }

        for (roleName in associations) {
            role = associations[roleName];
            item = role.getAssociatedItem(me);
            foreignKey = role.foreignKey || role.inverse.foreignKey;

            opts = undefined;

            if (options && Ext.isObject(options.associated)) {
                opts = options.associated[roleName];

                if (Ext.isObject(opts)) {
                    opts = Ext.Object.chain(opts);
                }
                else if (Ext.isObject(options.associated.default) && opts !== false) {
                    opts = Ext.Object.chain(options.associated.default);
                }
                else if (options.associated.default === false && opts !== true) {
                    opts = false;
                }
                else if (opts !== false) {
                    opts = undefined;
                }
            }

            if (!item || item.$gathering || opts === false) {
                continue;
            }

            if (item.isStore) {
                item.$gathering = 1;
                items = item.getData().items;
                // get the records for the store
                length = items.length;
                itemData = [];

                for (i = 0; i < length; ++i) {
                    // NOTE - we don't check whether the record is gathering here because
                    // we cannot remove it from the store (it would invalidate the index
                    // values and misrepresent the content). Instead we tell getData to
                    // only get the fields vs descend further.
                    record = items[i];
                    deep = !record.$gathering;
                    record.$gathering = 1;
                    if (!opts) {
                        if (options) {
                            associated = options.associated;
                            if (associated === undefined) {
                                options.associated = deep;
                                clear = true;
                            }
                            else if (!deep) {
                                options.associated = false;
                                clear = true;
                            }
                            opts = Ext.Object.chain(options);
                        }
                        else {
                            opts = deep ? me._getAssociatedOptions : me._getNotAssociatedOptions;
                        }

                        if (foreignKey) {
                            opts.associated = false;
                        }
                    }
                    itemData.push(record.getData(opts));
                    if (clear) {
                        options.associated = associated;
                        clear = false;
                    }
                    delete record.$gathering;
                }

                delete item.$gathering;
            }
            else {
                if (!opts) {
                    opts = Ext.Object.chain(options || me._getAssociatedOptions);
                    if (foreignKey) {
                        opts.associated = false;
                    }
                    else if (options && options.associated === undefined) {
                        opts.associated = true;
                    }
                }
                itemData = item.getData(opts);
            }

            result[roleName] = itemData;
        }

        delete me.$gathering;

        return result;
    }
    , getProxy: function () {
        var proxy = this.callParent(arguments), me = this;
        if (Ext.isFunction(proxy.setModelInstance)) {
            proxy.setModelInstance(this);
        }
        var api = proxy.getApi()
            , action;

        for (var p in api) {
            if (!Ext.ClassManager.getNameByAlias('data.operation.' + p) || !this[p]) {
                action = p;

                if (action) {
                    var funcStr = Ext.String.format("return this.save(options, '{0}')", action)
                        , func = new Function('options', funcStr);
                    this[action] = func.bind(me);
                }
            }
        }

        return proxy;
    }
    , create: function (options) {
        return this.save(options, 'create');
    }
    , update: function (options) {
        return this.save(options, 'update');
    }
    , validateData: function (options) {
        return this.save(options, 'validate');
    }
    , erase: function (options) {
        var me = this;
        me.erasing = true;

        options = options || {};

        me.drop(options.cascade);
        me.erasing = false;

        return this.save(options, 'destroy');
    }
    , save: function (options , action) {
        options = Ext.apply({}, options);

        var me = this,
            phantom = me.phantom,
            dropped = me.dropped,
            action = action ? action : (dropped ? 'destroy' : (phantom ? 'create' : 'update')),
            scope = options.scope || me,
            callback = options.callback,
            proxy = me.getProxy(),
            operation;

        options.records = [me];
        options.internalCallback = function (operation) {
            var args = [me, operation],
                success = operation.wasSuccessful();

            if (success) {
                Ext.callback(options.success, scope, args);
            } else {
                //Ext.callback(options.failure, scope, args); // <-- bypass internal failure as proxy already handles it
            }
            args.push(success);
            Ext.callback(callback, scope, args);
        };
        delete options.callback;

        operation = proxy.createOperation(action, options);

        // Not a phantom, then we must perform this operation on the remote datasource.
        // Record will be removed from the store in the callback upon a success response
        if (dropped && phantom) {
            // If it's a phantom, then call the callback directly with a dummy successful ResultSet
            operation.setResultSet(Ext.data.reader.Reader.prototype.nullResultSet);
            me.setErased();
            operation.setSuccessful(true);
        } else {
            operation.execute();
        }
        return operation;
    }
});