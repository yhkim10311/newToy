<#include "/layout/header.ftl" />
    <body>
<h1>글</h1>

<div class="row">
    <div class="col-md-6">
        <div>
            <label id="postId">${post.id}</label>번째 글
            <br>
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" value="${post.title}" >
        </div>
        <div>
            <label for="writer">작성자</label>
            <input type="text" class="form-control" id="writer" value="${post.userEmail}" readonly>
        </div>
        <div>
            <label for="content">내용</label>
            <textarea class="form-control" id="content" >${post.content}</textarea>
        </div>
        <div>
            댓글
        </div>

        <!-- 이 부분을 동적으로 받아오게 수정, html을 그려줄수 있게 호출 div id 걸어 놓고 호출, 리프레시 필요-->
        <#foreach comment in comments>
            <div style="padding-left: ${comment.depth * 40}px">
                <table id="myTable">
                    <tr>
                        <td style="display:none">${comment.id}</td>
                        <td style="display:none">${comment.depth}</td>
                        <td>${comment.content}</td>
                        <td>${comment.userEmail}</td>
                        <td>${comment.createdDate}</td>
                    </tr>
                </table>
            </div>
            <#if comment.comments??>
                <#foreach firstComment in comment.comments>
                    <div style="padding-left: ${firstComment.depth * 40}px">
                        <table id="myTable">
                            <tr>
                                <td style="display:none">${firstComment.id}</td>
                                <td style="display:none">${firstComment.depth}</td>
                                <td>${firstComment.content}</td>
                                <td>${firstComment.userEmail}</td>
                                <td>${firstComment.createdDate}</td>
                            </tr>
                        </table>
                    </div>
                    <#if firstComment.comments??>
                        <#foreach secondComment in firstComment.comments>
                            <div style="padding-left: ${secondComment.depth * 40}px">
                                <table id="myTable">
                                    <tr>
                                        <td style="display:none">${secondComment.id}</td>
                                        <td style="display:none">${secondComment.depth}</td>
                                        <td>${secondComment.content}</td>
                                        <td>${secondComment.userEmail}</td>
                                        <td>${secondComment.createdDate}</td>
                                    </tr>
                                </table>
                            </div>
                        </#foreach>
                    </#if>
                </#foreach>
            </#if>

        </#foreach>


        <form>
            <div class="form-group">
                <input type="text" class="form-control" id="comment" placeholder="댓글을 입력하세요">
            </div>
        </form>
        <a href="/" role="button" class="btn btn-secondary">뒤로</a>
        <button type="button" class="btn btn-primary" id="btn-comment">댓글등록</button>
        <button type="button" class="btn btn-primary" id="btn-delete">글 삭제</button>
        <button type="button" class="btn btn-primary" id="btn-update">글 수정</button>
    </div>

</div>
    </body>
<#include "/layout/footer.ftl" />