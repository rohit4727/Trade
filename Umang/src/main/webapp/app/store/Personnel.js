Ext.define('ui.store.Personnel', {
    extend: 'Ext.data.Store',

    alias: 'store.personnel',

    model: 'ui.model.Personnel',

    data: { items: [
        { job_name: 'job 1', date: "jeanluc.picard@enterprise.com", phone: "555-111-1111" },
        { job_name: 'job 1',     email: "worf.moghsson@enterprise.com",  phone: "555-222-2222" },
        { job_name: 'job 1',   email: "deanna.troi@enterprise.com",    phone: "555-333-3333" },
        { job_name: 'job 1',     email: "mr.data@enterprise.com",        phone: "555-444-4444" }
    ]},

    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            rootProperty: 'items'
        }
    }
});
