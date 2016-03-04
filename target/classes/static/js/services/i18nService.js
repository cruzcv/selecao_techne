app.service('i18n', function () {
        var self = this;
        this.setLanguage = function (language) {
            $.i18n.properties({
                name: 'messages',
                path: '/messages/',
                mode: 'map',
                language: language,
                callback: function () {
                    self.language = language;
                }
            });
        };
        this.setLanguage('pt');
    });
    