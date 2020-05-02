function displayStatusDay(day, month, year, username, event) {
    event.stopPropagation();
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: '/content/get-status-day',
        data:JSON.stringify({
            day : day,
            month : convertMonthToCalendarConstant(month),
            year : year,
            username: username
        }),
        success: function (data) {
            if (data.timeSheet == null){
                $('#failedModal').modal('show');
            } else {
                let workTimeModal = $('#work-time-modal');
                let outsideTimeModal = $('#outside-time-modal');
                let descriptionModal = $('#description');
                workTimeModal.empty();
                outsideTimeModal.empty();
                descriptionModal.empty();
                workTimeModal.append(convertToTimeFormat(data.timeSheet.workTime.hour, data.timeSheet.workTime.minute, data.timeSheet.workTime.second));
                outsideTimeModal.append(convertToTimeFormat(data.timeSheet.outsideTime.hour, data.timeSheet.outsideTime.minute, data.timeSheet.outsideTime.second));
                descriptionModal.append(data.timeSheet.description);

                $('#myModal').modal('show');
            }
        },
        error: function (e) {
            console.log(e.message);
        }
    })
}
function submitFormApplyingDayOff(username) {
    let description = $('#text-area-description')[0].value;
    let date = $('#day-off-picker')[0].value;
    let currentDay = new Date();
    if (date >= currentDay) {
        $.ajax({
            type : 'POST',
            contentType: 'application/json',
            url: '/content/apply-day-off',
            data: JSON.stringify({
                description : description,
                date : date,
                username : username
            }),
            success: function (data) {
                $('#applying-day-off-modal').modal('hide');
                activeMenu(1);
            },
            error: function (e) {
                console.log(e.message);
            }
        });
    } else {
        $('#applying-day-off-modal').modal('hide');
        $('#error-body-modal').append("This date was past. Applying day-off is allowed in future only.");
        $('#error-applying-day-off').modal('show');
    }

}

function convertMonthToCalendarConstant(month) {
    switch (month) {
        case 'January':
            return 0;
        case 'February':
            return 1;
        case 'March':
            return 2;
        case 'April':
            return 3;
        case 'May':
            return 4;
        case 'June':
            return 5;
        case 'July':
            return 6;
        case 'August':
            return 7;
        case 'September':
            return 8;
        case 'October':
            return 9;
        case 'November':
            return 10;
        case 'December':
            return 11;
    }
}

function convertToTimeFormat(hour, minute, second) {
    let timeString = '';
    if (hour < 10) {
        timeString += '0' + hour;
    } else {
        timeString += hour
    }
    timeString += ':';
    if (minute < 10) {
        timeString += '0' + minute;
    }else {
        timeString += minute;
    }
    timeString += ':';
    if (second < 10) {
        timeString += '0' + second;
    }else {
        timeString += second;
    }
    return timeString;
}
