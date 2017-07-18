(function (){
console.log('common load');

var CommonModule = {
    onload : function () { console.log('common loaded!'); },
    goBrfore : function(id, width) {
        console.log(id, width);
    }

};

$.fn.goBefore = function(width){
        console.log(width);
        console.log(this);
        this.animate({ "left": "-="+width+"px" }, "slow" );

}

$.fn.addCommentLi =function(source, context)
{
    template = Handlebars.compile(soruce);
    var html    = template(context);

    this.show();
    var $element_ul = parent.this;

    $(html).appendTo($element_ul);
}

function goBefore() {
    console.log('goBefore');
}


// CommonModule.goBefore = goBefore.bind(this);

// return CommonModule;

})();
