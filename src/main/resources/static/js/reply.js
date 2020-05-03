console.log("Reply Module......");
var replyService = (function () {
    function add(reply, callback, error) {

        $.ajax({
            type: "post",
            url : "/replies/new",
            data : JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success : function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error : function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        })
    }

    function getList(param, callback, error) {
        var boardId = param.boardId;
        var pageNum = param.pageNum;

        $.getJSON("/replies/pages/" + boardId + "/" + pageNum,
            function (data) {
                if (callback) {
                    callback(data);
                }
            }).fail(function (xhr, status, err) {
                if (error) {
                    error();
                }
        });
    }

    return {
        add:add,
        getList: getList
    };
})();