function loadUsername(username) {
    $.ajax({
        type: 'get',
        contentType: 'application/json',
        url: '/header/get-username',
        data: {username : username},
        dataType: "text",
        success: function(data) {
            $('#navbardrop').append("Hello, " + data);
        },
        error: function(e) {
            console.log(e.message);
        }
    });
}