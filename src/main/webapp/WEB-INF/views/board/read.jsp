<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"> Board Read </h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading"> Board Read Page </div>
            <!-- /.panel-heading -->

            <div class="panel-body">


                <div class="form-group">
                    <label>게시물 번호</label> <input class="form-control" name="boardId" value="${board.boardId}" readonly="readonly">
                </div>

                <div class="form-group">
                    <label>Title</label> <input class="form-control" name="title" value="${board.title}" readonly="readonly">
                </div>

                <div class="form-group">
                    <label>Content</label> <pre>${board.content}</pre>
                </div>

                <div class="form-group">
                    <label>Writer</label> <input class="form-control" name="writer" value="${board.writer}" readonly="readonly">
                </div>

                <button data-oper="modify" class="btn btn-default"> Modify </button>
                <button data-oper="list" class="btn btn-info" > List </button>


                <form id="operForm" action="/board/modify" method="get">
                    <input type="hidden" id="boardId" name="boardId" value="${board.boardId}">
                    <input type="hidden" id="pageNum" name="pageNum" value="${sessionScope.pageDto == null ?  1: sessionScope.pageDto.currentPage}">
                    <input type="hidden" id="amount" name="amount" value="${sessionScope.pageDto == null ? 5 : sessionScope.pageDto.amount}">
                    <input type="hidden" id="type" name="type" value="${sessionScope.pageDto == null ? "" : sessionScope.pageDto.type}">
                    <input type="hidden" id="keyword" name="keyword" value="${sessionScope.pageDto == null ? "" : sessionScope.pageDto.keyword}">
                </form>

            </div>
            <!-- end panel body -->
        </div>
        <!-- end panel -->
    </div>
    <!-- end col -->
</div>
<!-- end row -->

<div class="row">
    <div class="col-lg-12">

        <div class="panel panel-default">

            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"> </i> Reply
                <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right"> New Reply </button>
            </div>



            <div class="panel-body">
                <ul class="chat">
<%--                    start reply--%>

<%--                    end reply--%>
                </ul>
<%--                end ul--%>
            </div>
<%--            end .panel .chat-panel--%>
            <div class="panel-footer">

            </div>

        </div>

    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label> Reply </label>
                    <input class="form-control" name="reply" value="New Reply">
                </div>


            </div>
            <div class="modal-footer">

                <button id="modalRegisterBtn" type="button" class="btn btn-primary"> Register </button>
                <button id="modalCloseBtn" type="button" class="btn btn-default"> Close </button>

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<%@include file="../includes/footer.jsp"%>
<%--Javascript Module for reply--%>
<script type="text/javascript" src="/js/reply.js"></script>

<%--Test javascript --%>
<%--<script type="text/javascript">--%>
<%--    console.log("------JS TEST -------");--%>
<%--    var boardId = "${board.boardId}";--%>

<%--    // replyService.add(--%>
<%--    //     {content:"HelloWorld!", boardId:boardId},--%>
<%--    //     function(result) {--%>
<%--    //         alert("RESULT: " + result);--%>
<%--    //     }--%>
<%--    // )--%>


<%--    // replyService.getList({boardId:boardId, pageNum:2}, function (list) {--%>
<%--    //     console.log(list);--%>
<%--    // });--%>

<%--    // replyService.remove(5, function(count) {--%>
<%--    //     if (count === "success") {--%>
<%--    //         alert("REMOVED");--%>
<%--    //     }--%>
<%--    // }, function (err) {--%>
<%--    //     alert("ERROR");--%>
<%--    // });--%>
<%-- </script>--%>

<%--load replies--%>
<script type="text/javascript">
    var boardId = "${board.boardId}";
    var replyUL = $(".chat");

    showList(1);

    function showList(page) {
        replyService.getList({boardId: boardId, pageNum: page|| 1}, function(data) {
            var list = data.content;
            var replyCnt = data.totalElements;
            console.log(list);
            console.log(replyCnt);
            var str = "";
            if (list == null || list.length == 0) {
                replyUL.html("");
                return;
            }
            if (page == -1) {
                pageNum = data.totalPages;
                showList(pageNum);
                return;
            }


            for (var i = 0 ; i < list.length ; i++) {
                str += "<li class='left clearfix' data-replyid='" + list[i].replyId + "'>";
                str += " <div><div class='header'><strong class='primary-font'>" + list[i].writer + "</strong>";
                str += " <small class='pull-right text-muted'>" + list[i].createdDate + "</small></div>";
                str += '<button type="button" class="btn-sm btn-danger pull-right"> Delete </button>';
                str += "<p>" + list[i].content + "</p></div></li>";
            }
            replyUL.html(str);

            showReplyPage(replyCnt);
        });
    }
    // Modal 로 구현되는 부분 - 새 댓글 등록
    var modal = $(".modal");
    var modalInputReply = modal.find("input[name='reply']");

    var modalRegisterBtn = $("#modalRegisterBtn");

    $("#addReplyBtn").on("click", function(e) {
        modal.find("input").val("");
        modalRegisterBtn.show();

        $(".modal").modal("show");
    })

    modalRegisterBtn.on("click", function (e) {
        var reply = {content: modalInputReply.val(), boardId: boardId};
        replyService.add(reply, function(result) {
            alert(result);
            modal.find("input").val("");
            modal.modal("hide");

            showList(-1);
        });
    });

    // 댓글 삭제 버튼 클릭
    $(".chat").on("click", "button", function(e) {
        var replyId = $(this).closest("li").data("replyid");
        var check = confirm("정말로 삭제하시겠습니까?");
        if (check) {
            replyService.remove(replyId, function(result) {
                alert("성공적으로 삭제되었습니다");
                showList(1);
            })
        }
    })
    // 댓글 paging 목록 보여주기
    var pageNum = 1;
    var replyPageFooter = $(".panel-footer");

    function showReplyPage(replyCount) {
        var endNum = Math.ceil(pageNum / 10.0) * 10;
        var startNum = endNum - 9;

        var prev = startNum != 1;
        var next = false;

        if (endNum * 10 >= replyCount) {
            endNum = Math.ceil(replyCount / 10.0);
        }
        if (endNum * 10 < replyCount) {
            next = true;
        }

        var str = "<ul class='pagination pull-right'>";
        if (prev) {
            str += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>Previous</a></li>"
        }
        for (var i = startNum ; i <= endNum ; i++) {
            var active = pageNum == i ? "active" : "";
            str += "<li class='page-item " + active + "'><a class='page-link' href='" + i + "'>"+i+"</a></li>"
        }
        if (next) {
            str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>Previous</a></li>"
        }
        str += "</ul></div>";

        replyPageFooter.html(str);
    }

    replyPageFooter.on("click", "li a", function(e) {
        e.preventDefault();
        pageNum = $(this).attr("href");
        showList(pageNum);
    })

</script>

<script type="text/javascript">
    $(document).ready(function() {
        var operForm = $("#operForm");

        $("button[data-oper='modify']").on("click", function(e) {
            operForm.find("#pageNum").remove();
            operForm.find("#amount").remove();
            operForm.find("#keyword").remove();
            operForm.find("#type").remove();
            operForm.submit();
        });

        $("button[data-oper='list']").on("click", function (e) {
            operForm.find("#boardId").remove();
            operForm.attr("action", "/board/list");
            operForm.submit();
        });
    });
</script>