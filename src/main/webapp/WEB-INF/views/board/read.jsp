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
            </div>

            <div class="panel-body">
                <ul class="chat">
<%--                    start reply--%>
                    <li class="left clearfix" data-rno = "12">
                        <div>
                            <div class="header">
                                <strong class="primary-font">user00</strong>
                                <small class="pull-right text-muted">2018-01-01 13:13</small>
                            </div>
                                <p> Good Job! </p>
                        </div>
                    </li>
<%--                    end reply--%>
                </ul>
<%--                end ul--%>
            </div>
<%--            end .panel .chat-panel--%>
        </div>
    </div>
</div>


<%@include file="../includes/footer.jsp"%>
<%--Javascript Module for reply--%>
<script type="text/javascript" src="/js/reply.js"></script>

<%--Test javascript --%>
<%--<script type="text/javascript">--%>
<%--    console.log("------JS TEST -------");--%>
<%--    var boardId = "${board.boardId}";--%>

    // replyService.add(
    //     {content:"HelloWorld!", boardId:boardId},
    //     function(result) {
    //         alert("RESULT: " + result);
    //     }
    // )


    // replyService.getList({boardId:boardId, pageNum:2}, function (list) {
    //     console.log(list);
    // });

    // replyService.remove(5, function(count) {
    //     if (count === "success") {
    //         alert("REMOVED");
    //     }
    // }, function (err) {
    //     alert("ERROR");
    // });
<%-- </script>--%>

<%--load replies--%>
<script type="text/javascript">
    var boardId = "${board.boardId}";
    var replyUL = $(".chat");

    showList(1);

    function showList(pageNum) {
        replyService.getList({boardId: boardId, pageNum: pageNum || 1}, function(data) {
            var list = data.content;
            var str = "";
            if (list == null || list.length == 0) {
                replyUL.html("");
                return;
            }
            for (var i = 0 ; i < list.length ; i++) {
                str += "<li class='left clearfix' replyId='" + list[i].replyId + "'>";
                str += " <div><div class='header'><strong class='primary-font'>" + list[i].writer + "</strong>";
                str += " <small class='pull-right text-muted'>" + list[i].createdDate + "</small></div>";
                str += "<p>" + list[i].content + "</p></div></li>";
            }
            replyUL.html(str);
        });
    }

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