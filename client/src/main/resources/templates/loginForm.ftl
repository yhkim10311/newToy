<#include "/layout/header.ftl" />
<body>
<h1>로그인</h1>

<div class="col-md-12">
    <div class="col-md-4">
        <form>
            <div class="form-group">
                <label for="principal">아이디</label>
                <input type="text" class="form-control" id="principal" placeholder="아이디를 입력하세요">
            </div>
            <div class="form-group">
                <label for="credentials">비밀번호</label>
                <input type="password" class="form-control" id="credentials" placeholder="비밀번호를 입력하세요">
            </div>
        </form>
        <a href="/" role="button" class="btn btn-secondary">취소</a>
        <button type="button" class="btn btn-primary" id="btn-login">로그인</button>
    </div>
</div>
</body>
<#include "/layout/footer.ftl" />