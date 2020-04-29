<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Tables</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Board List Page
                        <a href="/board/register"><button id="regBtn" type="button" class="btn btn-xs pull-right"> Register New Board </button></a>
                    </div>
                    <!-- /.panel-heading -->


                    <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>#번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                    <th>수정일</th>
                                    <th>댓글 수</th>
                                    <th>좋아요</th>
                                </tr>
                                </thead>
                                <c:forEach items="${list}" var="board">
                                    <tr>
                                        <td>${board.boardId}</td>
                                        <td><a href="/board/read?boardId=${board.boardId}"> ${board.title} </a></td>
                                        <td>${board.writer}</td>
                                        <fmt:parseDate value="${board.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedCreatedDate" type="both"/>
                                        <fmt:parseDate value="${board.modifiedDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedModifiedDate" type="both"/>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedCreatedDate}"/></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedModifiedDate}"/></td>
                                        <td>${board.replyCount}</td>
                                        <td>${board.likeCount}</td>
                                    </tr>
                                </c:forEach>

                            </table>
                        <div class="pull-right">
                            <ul class="pagination">
                                <c:if test="${pageDto.prev}">
                                    <li class="paginate_button previous"><a href="${pageDto.startPage - 1}"> Previous </a> </li>
                                </c:if>

                                <c:forEach var="num" begin="${pageDto.startPage}" end="${pageDto.endPage}">
                                    <li class='paginate_button ${pageDto.currentPage == num ? "active":""}'><a href="${num}"> ${num}</a> </li>
                                </c:forEach>

                                <c:if test="${pageDto.next}">
                                    <li class="paginate_button next"><a href="${pageDto.endPage + 1}"> Next </a> </li>
                                </c:if>

                            </ul>
                            <form id="actionForm" action="/board/list" method="get">
                                <input type="hidden" name="pageNum" value="">
                                <input type="hidden" name="amount" value="">
                            </form>

                        </div>
                        <%--    end Pagination--%>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
<%@ include file="../includes/footer.jsp"%>
<script type="text/javascript">
    $(document).ready(function() {
       var actionForm = $("#actionForm");
       $(".paginate_button a").on("click", function (e) {

           e.preventDefault();
           actionForm.find("input[name='pageNum']").val($(this).attr("href"));
           actionForm.submit();
       });

    });
</script>