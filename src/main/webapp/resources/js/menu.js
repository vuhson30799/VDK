function activeMenu(index) {
    $(".list-group-item").each(function() {
        if ($(this)[0].id === "menu5") {
            $(this).attr("class","list-group-item final-list-group");
        } else {
            $(this).attr("class","list-group-item");
        }

    });
    $("#menu" + index).attr("class","list-group-item active");
    displayCalendarStatus();
}

function displayCalendarStatus() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/home",
        timeout: 100000,
        success: function (data) {
            let contentTemplate = $("#content-template");
            contentTemplate.empty();
            contentTemplate.append(data);
        },
        error: function (e) {
            console.log("error: " + e);
        }
    });
}

function displayHistoryCalendar(month, year, option) {
    let monthCalendar = convertMonthToCalendarConstant(month);
    switch (option) {
        case 'LAST_MONTH':
            monthCalendar--;
            break;
        case 'NEXT_MONTH':
            monthCalendar++;
            break;
    }
    let url = "/home?month=" + monthCalendar + "&year=" + year;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        timeout: 100000,
        success: function (data) {
            let contentTemplate = $("#content-template");
            contentTemplate.empty();
            contentTemplate.append(data);
        },
        error: function (e) {
            console.log("error: " + e);
        }
    });
}
