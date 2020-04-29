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

                <form role="form" action="/board/modify" method="post">

                    <div class="form-group" style="display: none">
                        <label>BoardId</label> <input class="form-control" name="boardId" value="${board.boardId}" >
                    </div>

                    <div class="form-group">
                        <label>Title</label> <input class="form-control" name="title" value="${board.title}" >
                    </div>

                    <div class="form-group">
                        <label>Text area</label> <textarea class="form-control" rows="3" name="content">${board.content}</textarea>
                    </div>

                    <input type="hidden" id="pageNum" name="pageNum" value="${sessionScope.pageDto == null ?  1: sessionScope.pageDto.currentPage}">
                    <input type="hidden" id="amount" name="amount" value="${sessionScope.pageDto == null ? 5 : sessionScope.pageDto.amount}">

                    <button type="submit" data-oper="modify" class="btn btn-default"> Modify </button>
                    <button type="submit" data-oper="remove" class="btn btn-danger"> Delete </button>
                    <button type="submit" data-oper="list" class="btn btn-info"> List </button>

                </form>
            </div>
            <!-- end panel body -->
        </div>
        <!-- end panel -->
    </div>
    <!-- end col -->
</div>
<!-- end row -->

<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">
    $(document).ready( function() {
        var formObj = $("form");

        $('button[type="submit"]').on("click", function(e) {

            e.preventDefault();
            var operation = $(this).data("oper");
            console.log(operation);

            if (operation === "remove") {
                formObj.attr("action", "/board/remove");
                formObj.find("#pageNum").remove();
                formObj.find("#amount").remove();

            } else if (operation === "list") {
                formObj.attr("action", "/board/list").attr("method", "get");
                formObj.find($(".form-control")).remove();
                formObj.submit();
            }
            formObj.submit();

        })
    })
</script>