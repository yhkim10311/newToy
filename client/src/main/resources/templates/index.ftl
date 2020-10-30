<#include "/layout/header.ftl" />
<body>
<h1>게시판</h1>
<div class="row">
    <div class="col-md-6">
        <a href="/post" role="button" class="btn btn-primary">글 등록</a>
        <#if user??>
            <a href="/logout" class="btn btn-success active" role="button" id="btn-logout">Logout</a> logged in as ${user}
        <#else>
            <a href="/login" class="btn btn-success active" role="button" id="btn-loginPage">Login</a>
            <a href="/join" class="btn btn-success active" role="button">회원가입</a>
        </#if>
    </div>
</div>

<table class="table table-horizontal table-bordered">
    <thead class="thead-strong">
    <tr>
        <td>번호</td>
        <td>작성자</td>
        <td>제목</td>
        <td>작성일</td>
    </tr>
    </thead>
    <#foreach post in posts>
        <tr onclick="location.href='/post/${post.id}'" style="cursor: hand">
            <td>${post.id}</td>
            <td>${post.userEmail}</td>
            <td>${post.title}</td>
            <td>${post.createdDate}</td>
        </tr>
    </#foreach>
</table>
</body>
<#include "/layout/footer.ftl" />