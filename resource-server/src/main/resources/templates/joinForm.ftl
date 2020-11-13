<#include "/layout/header.ftl" />

<h1>Resource Server 회원가입</h1>

<div class="col-md-12">
    <div class="col-md-4">
        <form>
            <div class="form-group">
                <label for="principal">이메일</label>
                <input type="email" class="form-control" id="principal" placeholder="이메일을 입력하세요">
            </div>
            <div class="form-group">
                <label for="name">이름</label>
                <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요">
            </div>
            <div class="form-group">
                <label for="credentials">비밀번호 (8자리 이상)</label>
                <input type="password" class="form-control" id="credentials" placeholder="비밀번호를 입력하세요">
            </div>
        </form>
        <a href="http://yh-toy-service.tk/" class="btn btn-secondary" role="button">취소</a>
        <button type="button" class="btn btn-primary" id="btn-join">등록</button>
    </div>
</div>

<#include "/layout/footer.ftl" />