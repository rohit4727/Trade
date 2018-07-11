Ext.define('ui.proxy.WebPartProxy', {
    extend: 'Ext.data.AjaxProxy'
    , alias: 'proxy.webpart'
    , timeout: 120000    
    , outputRenderType: {
        Html: 'Html'
        , Json: 'Json'
        , Xml: 'Xml'
        , Xslt: 'Xslt'
        , None: 'None'
    }
    , dataSourceType: {
        ContextXml: 'ContextXml'
        , DbXml: 'DbXml'
        , DistributedDbJson: 'DistributedDbJson'
        , HttpXml: 'HttpXml'
        , LocalXml: 'LocalXml'
        , ContextJson: 'ContextJson'
        , DbJson: 'DbJson'
        , HttpJson: 'HttpJson'
        , LocalJson: 'LocalJson'
    }
    , failureType: {
        ReturnCodeFailure: 'RCF'
        , RootFailure: 'RF'
        , SystemFailure: 'SF'
        , NetworkFailure: 'NF'
        , ValidationFailure:'VF'
    }
    , defaultPostOptions: {
        associated: true
    }
    , config: {
        dataSource: null
        , dataSourceType: null
        , excludeHttpContext: 0
        , includeSessionContext: 0
        , includeUtils: 0
        , outputRenderFile: null
        , outputRenderFileForExcel: null
        , outputRenderFileForPDF: null
        , outputRenderType: null
        , returnCode: 20
        , message: null
        , displayMessage: 'Oopps, some errors occured, please try again later'
        , modelInstance: null
        , defaultUrl: ''
        , displaySuccessMessage: true
        , postOptions: null
        , defaultApiSuccessMessage:{
            create: 'Record has been created'
            , update: 'Record has been updated'
            , destroy: 'Record has been deleted'
        }
        , api: {
            create: undefined
            , read: undefined
            , update: undefined
            , destroy: undefined
            , validate: undefined
        }
        , defaultActionMethods: {
            create: 'POST'
            , read: 'GET'
            , update: 'POST'
            , destroy: 'POST'
            , validate: 'POST'
        }
        , reader: {
            type: 'json'
            , rootProperty: 'data'
        }
    }
    , constructor: function (config) {
        config.dataSourceType = config.dataSourceType || this.dataSourceType.DbJson;
        config.outputRenderType = config.outputRenderType || this.outputRenderType.Json;

        this.callParent(arguments);
        this._setActionModels();
    }

    , privates: {
        _setActionModels:function(){
            this.modelId = Ext.id();
            var proxyConfig = this.getApi() || {};
            for (var action in proxyConfig) {
                var actionConfig = proxyConfig[action];
                if (Ext.isObject(actionConfig)) {
                    var fields = actionConfig.fields;
                    if (Ext.isArray(fields)) {
                        var id = this.modelId;
                        this[action + '-model-' + id] = new (Ext.define(action + '-model-' + id, {
                            extend: 'ui.model.WebPartModel'
                            , fields: fields
                            , validators: actionConfig.validators
                        }))();
                    }
                }
            }
        }
        , _setProxyValidators: function (action) {
            var proxyConfig = this.getApi() || {}
                , actionConfig = proxyConfig[action]
                , modelInstance = this.modelInstance;

            if (modelInstance == null) {
                modelInstance = this[action + '-model-' + this.modelId];
            }
            if (modelInstance) {
                var validators = actionConfig.validators;

                if (validators) {
                    for (name in validators) {
                        var field = modelInstance.getField(name);
                        if (field) {
                            field.setModelValidators(validators[name]);
                        }
                    }
                }
            }
        }
        , _resetProxyValidators: function (action) {
            var proxyConfig = this.getApi() || {}
                , actionConfig = proxyConfig[action]
                , modelInstance = this.modelInstance;

            if (modelInstance == null) {
                modelInstance = this[action + '-model-' + this.modelId];
            }
            if (modelInstance) {
                var validators = actionConfig.validators
                    , fields = modelInstance.getFields();

                if (validators) {
                    for (var i = 0; i < fields.length; i++) {
                        var field = fields[i]
                            , validator = validators[field.name] || null;
                        field.setModelValidators(validator);
                    }
                }
            }
        }
        , _getValidation: function (action) {
            var proxyConfig = this.getApi() || {}
                , modelInstance = this.modelInstance;

            if (modelInstance == null) {
                modelInstance = this[action + '-model-' + this.modelId];
            }
            if (modelInstance) {
                return modelInstance.getValidation(true);
            }
            else {
                return { dirty: false, data: {} };
            }
        }
        , _internalFailure: function (scope, operation, failureType, requestOptions) {
            if (Ext.isFunction(requestOptions.failure)) {
                requestOptions.failure.apply(scope, [this, operation, failureType]);
            }
            var action = operation.getAction()
                message = '', type = 'failure'
                , actionConfig = this.getApi()[action];

            if (actionConfig.displayFailureMessage != false) {
                if (failureType == this.failureType.ValidationFailure) {
                    var validation = this._getValidation(action).data;
                    for (var p in validation) {
                        if (validation[p] != true) {
                            message += Ext.String.format('<div>' + validation[p] + '</div>');
                        }
                    }
                    type = 'warning';
                }
                else if (failureType) {
                    message = this.getDisplayMessage();
                }

                Ext.toast(message);
                
//                warning.setData({
//                    type: type
//                    , message: message
//                });
//                warning.display();
            }
        }
        , _internalSuccess: function (scope, operation, requestOptions) {
            var me = this
                , action = operation.getAction()
                , actionConfig = me.getApi()[action]
                , warning
                , message;

            if (actionConfig.displaySuccessMessage !== true) {
                return;
            }

            if (action == 'read') {
                return;
            }
            message = me.getDisplayMessage() || me.defaultApiSuccessMessage[action];

            if (actionConfig.successMessage) {
                message = actionConfig.successMessage;
            }
            Ext.toast(message);            
        }
        , _internalCallback: function (scope, operation, success, requestOptions) {
            if (Ext.isFunction(requestOptions.callback)) {
                requestOptions.callback.apply(scope, [this, operation, success]);
            }
            var maskCmp = requestOptions.maskCmp;
            if (maskCmp && maskCmp.unmask && maskCmp.destroyed != true) {
                maskCmp.unmask();
            }
        }
        , _internalProgress: function (progressArgs, progressEvent) {
            var scope = progressArgs[0]
                , requestOptions = progressArgs[1]
                , requestConfig = progressArgs[2];

            if (Ext.isFunction(requestOptions.progress)) {
                requestOptions.progress.apply(scope || this, [this, progressEvent]);
            }
        }
        , _applyModelParams: function (api, action, initialParams) {
            var modelInstance = this.modelInstance
                , actionConfig = api[action]
                , paramsWrapper = actionConfig.paramsWrapper
                , postOptions = Ext.apply(this.defaultPostOptions, actionConfig.postOptions || {})
                , params = {}
                , operationProxyModelInstance = this[action + '-model-' + this.modelId]
                , applyParams = function (primaryModelInstance, secondaryModelInstance, initialParams, params, actionConfig) {
                    var fields = secondaryModelInstance ? secondaryModelInstance.getFields() : (actionConfig.fields || primaryModelInstance.getFields())
                        , data
                        , field
                        , name
                        , primaryModelInstanceValue
                        , secondaryModelInstanceValue
                        , value;

                    if (!Ext.isArray(fields)) {
                        return;
                    }

                    data = primaryModelInstance.getData(postOptions);

                    for (var i = 0; i < fields.length; i++) {
                        field = fields[i];

                        if (Ext.isString(field)) {
                            name = field;
                        }
                        else if (Ext.isObject(field)) {
                            name = field.name;
                        }
                        else {
                            continue;
                        }

                        if (name == primaryModelInstance.idProperty && (action == 'create')) {
                            continue;
                        }

                        primaryModelInstanceValue = data[name];
                        secondaryModelInstanceValue = (secondaryModelInstance && name != 'id' ? secondaryModelInstance.get(name) : null);
                        value = secondaryModelInstanceValue ? secondaryModelInstanceValue : primaryModelInstanceValue;

                        params[name] = value;

                        if (Ext.isEmpty(params[name]) && !Ext.isEmpty(initialParams)) {
                            params[name] = initialParams[name];
                            delete initialParams[name];
                        }
                    }
                }
                , unParams = function (modelInstance, params, actionConfig) {
                    var unfields = actionConfig ? actionConfig.unfields : [];

                    if (Ext.isArray(unfields)) {
                        for (var i = 0; i < unfields.length; i++) {
                            var field = modelInstance.getField(unfields[i]);
                            delete params[field.name];
                        }
                    }
                }

            if (!modelInstance && operationProxyModelInstance) {
                applyParams(operationProxyModelInstance, null, initialParams, params, actionConfig);
                unParams(operationProxyModelInstance, params, actionConfig);
            }
            else if(modelInstance || operationProxyModelInstance){
                applyParams(modelInstance, operationProxyModelInstance, initialParams, params, actionConfig);
                unParams(modelInstance, params, actionConfig);
            }

            if (paramsWrapper) {
                var obj = {};
                obj[paramsWrapper] = params;
                params = obj;
            }

            return params;
        }
    }
    , buildRequest: function (operation) {
        var me = this,
            initialParams = Ext.apply({}, operation.getParams()),
            // Clone params right now so that they can be mutated at any point further down the call stack
            params = Ext.applyIf(initialParams, me.getExtraParams() || {}),
            request, operationId, idParam;
        //copy any sorters, filters etc into the params so they can be sent over the wire
        Ext.applyIf(params, me.getParams(operation, params));
        // Set up the entity id parameter according to the configured name.
        // This defaults to "id". But TreeStore has a "nodeParam" configuration which
        // specifies the id parameter name of the node being loaded.
        operationId = operation.getId();
        idParam = me.getIdParam();
        if (operationId !== undefined && params[idParam] === undefined) {
            params[idParam] = operationId;
        }
        request = new Ext.data.Request({
            params: params,
            action: operation.getAction(),
            records: operation.getRecords(),
            url: operation.getUrl(),
            operation: operation,
            // this is needed by JsonSimlet in order to properly construct responses for
            // requests from this proxy
            proxy: me
        });
        request.setUrl(me.buildUrl(request));
        /*
         * Save the request on the Operation. Operations don't usually care about Request and Response data, but in the
         * ServerProxy and any of its subclasses we add both request and response as they may be useful for further processing
         */
        operation.setRequest(request);
        return request;
    }

    , setParams: function (params, action) {
        action = action || 'read';
        if (action) {
            var modelInstance = this[action + '-model-' + this.modelId];
            if (modelInstance) {
                modelInstance.reject();
                modelInstance.set(params || {});
            }
        }        
    }
    , getParams: function (operation, initialParams) {
        var defaultParams = this.callParent(arguments);

        var api = this.getApi()
            , action = operation.getAction()
            , actionConfig = api[action];

        if (!Ext.isEmpty(actionConfig)) {
            if (Ext.isString(actionConfig)) {
                defaultParams.dataSource = actionConfig;
            }
            else if (Ext.isObject(actionConfig)) {
                if (Ext.isEmpty(actionConfig.url)) {
                    Ext.apply(defaultParams, this.getWebPartConfig(actionConfig));
                }
                var params = this._applyModelParams(api, action, initialParams);
                defaultParams.params = Ext.util.JSON.encode(params);
            }
        }
        else {
            Ext.apply(defaultParams, this.getWebPartConfig());
        }

        return defaultParams;
    }

    , getModelFieldsData: function () {
        if (this.modelInstance) {
            return this.modelInstance.getData();
        }
        else {
            var data = {};
            var fields = this.model.getFields();

            for (var i = 0; i < fields.length; i++) {
                var field = fields[i];
                data[field.name] = field.value;
            }
            return data;
        }        
    }

    , getWebPartConfig: function (config) {
        config = config || this;
        return {
            dataSource: config.dataSource || this.dataSource
            , dataSourceType: config.dataSourceType || this.dataSourceType
            , excludeHttpContext: config.excludeHttpContext || this.excludeHttpContext
            , includeSessionContext: config.includeSessionContext || this.includeSessionContext
            , includeUtils: config.includeUtils || this.includeUtils
            , outputRenderFile: config.outputRenderFile || this.outputRenderFile
            , outputRenderFileForExcel: config.outputRenderFileForExcel || this.outputRenderFileForExcel
            , outputRenderFileForPDF: config.outputRenderFileForPDF || this.outputRenderFileForPDF
            , outputRenderType: config.outputRenderType || this.outputRenderType
        }
    }
    , buildUrl: function (request) {
        var me = this,
            urlCfg = me.getUrl(request), url = this.defaultUrl;

        if (urlCfg.url) {
            url = urlCfg.url;
        }

        if (me.getNoCache()) {
            url = Ext.urlAppend(url, Ext.String.format("{0}={1}", me.getCacheString(), Ext.Date.now()));
        }

        return url;
    }

    , doRequest: function (operation) {
        var action = operation.getAction();

        this._resetProxyValidators(action);
        this._setProxyValidators(action);

        var validation = this._getValidation(action)
            , requestOptions = operation.config || null
            , scope = requestOptions.scope || this;

        if (validation.dirty === false) {
            return this.callParent(arguments);
        }
        else {
            this._internalFailure(scope, operation, this.failureType.ValidationFailure, requestOptions);
            this._internalCallback(scope, operation, false, requestOptions);
            if (Ext.isFunction(requestOptions.internalCallback)) {
                requestOptions.internalCallback.apply(scope, [operation]);
            }
        }
    }
    , sendRequest: function (request) {
        var operation = request.getOperation()
            , requestOptions = operation.config || null
            , scope = requestOptions.scope || this
            , requestConfig = request.getCurrentConfig()
            , params = requestConfig.params || {}
            , maskCmp = requestOptions.maskCmp;

        try {
            if (maskCmp && maskCmp.mask && (maskCmp.rendered || maskCmp.dom)) {
                maskCmp.mask('Please wait...');
            }
            delete requestConfig.formData;
            delete requestConfig.rawData;
            delete requestConfig.binaryData;
            delete requestConfig.xmlData;
            delete requestConfig.jsonData;

            requestConfig.progress = this._internalProgress.bind(this, [requestOptions.scope, requestOptions, requestConfig]);

            for (var p in requestConfig.params) {
                if (p != 'params' && p != 'dataSource' && p != 'dataSourceType' 
                    && p != 'excludeHttpContext' && p != 'includeSessionContext'
                    && p != 'includeUtils' && p != 'outputRenderFile'
                    && p != 'outputRenderFileForExcel' && p != 'outputRenderFileForPDF' && p != 'outputRenderType') {
                    delete requestConfig.params[p];
                }
            }

            request.setRawRequest(Ext.Ajax.request(requestConfig));
            this.lastRequest = request;
            return request;
        }
        catch (ex) {
            this._internalFailure(scope, null, this.failureType.NetworkFailure, requestOptions);
        }
    }
    , createRequestCallback: function (request, operation) {
        var me = this;
        return function (options, success, response) {
            if (request === me.lastRequest) {
                me.lastRequest = null;
            }
            me.processResponse(success, operation, request, response);
        };
    }
    , isWebPartAction: function (action) {
        var actionConfig = this.getApi()[action];
        return Ext.isEmpty(actionConfig.url);
    }
    , getMethod: function (request) {
        var actions = this.getActionMethods(),
            action = request.getAction(),
            method;

        if (actions) {
            method = actions[action];
        }

        if (action == 'read' || (action != 'create' && action != 'update' && action != 'destroy')) {
            method = this.isWebPartAction(action) ? 'POST' : method || 'POST';
        }

        return method || this.defaultActionMethods[action];
    }

    , processResponse: function (success, operation, request, response) {
        var me = this,
            exception, reader, resultSet, failureType = '';
        // Processing a response may involve updating or committing many records
        // each of which will inform the owning stores, which will ultimately
        // inform interested views which will most likely have to do a layout
        // assuming that the data shape has changed.
        // Bracketing the processing with this event gives owning stores the ability
        // to fire their own beginupdate/endupdate events which can be used by interested
        // views to suspend layouts.
        me.fireEvent('beginprocessresponse', me, response, operation);
        if (success === true) {            
            reader = me.getReader();
            if (response.status === 204) {
                resultSet = reader.getNullResultSet();
            }
            else {
                resultSet = reader.read(me.extractResponseData(response), {
                    // If we're doing an update, we want to construct the models ourselves.
                    recordCreator: operation.getRecordCreator()
                });
            }

            var responseText = response.responseText
                , json = Ext.util.JSON.decode(responseText);

            if (json.meta) {
                this.returnCode = json.meta.return_code;
                this.message = json.meta.message;
                this.displayMessage = json.meta.display_message || this.displayMessage;
                this.e_log = json.meta.error;

                if (this.returnCode != 0) {
                    failureType = this.failureType.ReturnCodeFailure;
                }
            }
            else {
                this.returnCode = 20;
                this.message = 'Meta data is missing.';
                failureType = this.failureType.RootFailure;
            }

            resultSet.setSuccess(Ext.isEmpty(failureType));
            resultSet.setMessage(this.message);
            operation.process(resultSet, request, response);
            exception = !operation.wasSuccessful();
        }
        else if(response.aborted != true) {
            failureType = this.failureType.SystemFailure;
            this.returnCode = response.status;
            this.message = response.statusText || 'Server error occured';
            me.setException(operation, response);
            exception = true;
        }
        if (failureType) {
            me.fireEvent('exception', me, response, operation);
        }        

        me.afterRequest(request, success);
        // Tell owning store processing has finished.
        // It will fire its endupdate event which will cause interested views to 
        // resume layouts.
        me.fireEvent('endprocessresponse', me, response, operation);
        var requestOptions = operation.config || null;
        
        if (failureType) {
            // callfailure
            this._internalFailure(requestOptions.scope || this, operation, failureType, requestOptions);
        }
        else {
            // call success
            this._internalSuccess(requestOptions.scope || this, operation, requestOptions);
        }
        //call callback
        this._internalCallback(requestOptions.scope || this, operation, !failureType, requestOptions);
    }

    , export: function (type) {
        type = type || 'EXCEL';
        var exportParam = 'ExportToExcel'
        if (type.toUpperCase == 'EXCEL') {
            exportParam = 'ExportToPDF'
        }
        var params = this.getParams();
        params[exportParam] = 1;
        Ext.create({
            autoEl: {
                tag: 'form'
                , method: 'post'
                , target: '_blank'
                , 'accept-charset': 'UTF-8'
                , action: url
                , frameBorder: 0
                , style: 'position:absolute;top:-10000px; left:-10000px;height:0;width:0;'
            }
            , renderTo: document.body
            , id: 'exportForm'
            , xtype: 'component'
        })
        var formEl = Ext.getCmp('exportForm').el.dom
            , hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", 'json');
        hiddenField.setAttribute("value", Ext.util.JSON.encode(params));
        formEl.appendChild(hiddenField);

        formEl.submit();

        setTimeout(function () {
            var form = document.getElementById('exportForm');
            if (form) {
                Ext.get(form).remove();
            }
        }, 1000 * 60 * 5);
    }
    , validate: function () {
        return this.doRequest.apply(this, arguments);
    }
    , destroy: function () {
        var proxyConfig = this.getApi() || {};
        for (var action in proxyConfig) {
            if (this[action + '-model-' + this.modelId]) {
                Ext.destroy(this[action + '-model-' + this.modelId]);
            }            
        }
        this.callParent(arguments);        
    }
    , createOperation: function (action, config) {
        var api = this.getApi()
            , operation, me = this;
        if (api[action] && Ext.ClassManager.getNameByAlias('data.operation.' + action)) {
            operation = this.callParent(arguments);
        }
        else if (api[action]) {
            Ext.define('Ext.data.operation.' + Ext.String.capitalize(action), {
                extend: 'Ext.data.operation.Read'
                , alias: 'data.operation.' + action
                , action: action
            });
            operation = Ext.create('Ext.data.operation.' + Ext.String.capitalize(action), config);
            operation.setProxy(this);
            this.pendingOperations[operation._internalId] = operation;
        }

        return operation;
    }
});
