CKEDITOR.editorConfig = function( config ) {
    config.language = 'pl';
    config.stylesSet = [
        {
            name: 'Text center',
            element: 'div',
            attributes: {'class':'text-center'}
        },
        {
            name: 'Paragraph',
            element: 'p'
        },
        {
            name: 'Quote',
            element: 'blockquote',
            attributes: {'class':'blockquote'}
        }
    ]
    config.contentsCss = '/css/bootstrap.css';
};