$.get("/user", function(data) {
    $("#user").html(data.name);
    $(".unauthenticated").hide()
    $(".authenticated").show()
});

var logout = function() {
    $.post("/logout", function() {
        $("#user").html('');
        $(".unauthenticated").show();
        $(".authenticated").hide();
    })
    return true;
}

$.ajaxSetup({
    beforeSend : function(xhr, settings) {
        if (settings.type == 'POST' || settings.type == 'PUT'
            || settings.type == 'DELETE') {
            if (!(/^http:.*/.test(settings.url) || /^https:.*/
                .test(settings.url))) {
                // Only send the token to relative URLs i.e. locally.
                xhr.setRequestHeader("X-XSRF-TOKEN",
                    Cookies.get('XSRF-TOKEN'));
            }
        }
    }
});