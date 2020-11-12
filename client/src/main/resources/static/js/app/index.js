var flag = false;
var selectedComment;
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

        $('#btn-comment').on('click', function () {
            _this.comment();
        });

        $('#btn-loginPage').on('click', function () {
            _this.loginPage();
        });

        $("#myTable tr").click(function(e) {
            if(!$(this).hasClass("highlight") && this.children[1].innerText>=0 && this.children[1].innerText<2){
                $("#myTable tr").removeClass("highlight");
                $("#myTable tr").css('background','#ffffff');
                $(this).addClass("highlight");
                $(this).css('background','#7a7777');
                flag = true;
                selectedComment = this;
            }else{
                $("#myTable tr").removeClass("highlight");
                $("#myTable tr").css('background','#ffffff');
                flag = false;
            }
        });

    },
    save : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/post',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#postId').text();

        $.ajax({
            type: 'PUT',
            url: '/api/post/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#postId').text();

        $.ajax({
            type: 'DELETE',
            url: '/api/post/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
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
            window.location.href = '/';
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
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    post : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/post',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('글이 작성 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    comment : function () {
        var data = {
            content: $('#comment').val(),
            postId: $('#postId').text()
        };
        var commentId='';
        if(flag){
            commentId = '/'+selectedComment.children[0].innerText;
            data.depth = selectedComment.children[1].innerText;
        }
        $.ajax({
            type: 'POST',
            url: '/api/post/'+data.postId+'/comment'+commentId,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('댓글이 작성 되었습니다.');
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    loginPage : function () {
        var data = {
            content: $('#comment').val(),
            postId: $('#postId').text()
        };
        $.ajax({
            url: 'http://localhost:9000/authorize'
        }).done(function(data) {
            document.location.href="http://localhost:9000/authorize";
        });
    }

};

main.init();