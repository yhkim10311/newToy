let token;
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-join').on('click', function () {
            _this.join();
        });

        $('#btn-login').on('click', function () {
            _this.login();
        });

        $('#btn-post').on('click', function () {
            _this.post();
        });
    },
    join : function () {
        var data = {
            name: $('#name').val(),
            principal: $('#principal').val(),
            credentials: $('#credentials').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/user/join',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원 가입이 완료되었습니다.');
            history.back();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    login : function () {
        var data = {
            principal: $('#principal').val(),
            credentials: $('#credentials').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/auth',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('로그인 되었습니다.');
            document.location.href = data.response.redirectUrl
                +'?auth_code='+data.response.authCode
                +'&user_id='+data.response.userDto.email;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();