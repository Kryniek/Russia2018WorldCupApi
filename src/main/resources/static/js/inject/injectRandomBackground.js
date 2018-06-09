var injectRandomBackground = function () {
    (function init() {
        var headNode = document.head || document.getElementsByTagName('head')[0];
        var styleNode = decorateStyleNode();

        headNode.appendChild(styleNode);
    })();

    function decorateStyleNode() {
        var newBackgroundImageCssText = getNewBackgroundImageCssText();
        var styleNode = document.createElement('style');
        var styleNodeStyleSheet = styleNode.styleSheet;

        styleNode.type = 'text/css';

        if (!!styleNodeStyleSheet) {
            styleNodeStyleSheet.cssText = newBackgroundImageCssText;
        } else {
            styleNode.appendChild(document.createTextNode(newBackgroundImageCssText));
        }

        return styleNode;
    };

    function getNewBackgroundImageCssText() {
        var randomNumber = Math.floor((Math.random() * 7) + 1);
        var backgroundImageLocation = '../../img/background/';

        return 'body::after { background-image: url("../../img/background/background' + randomNumber + '.jpg"); }';
    };
};