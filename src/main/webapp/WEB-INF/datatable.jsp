<html lang="en">
<head>
    <title>user datatable</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <link href="css/bootstrap.css" rel="stylesheet"/>
    <link href="css/dataTables.bootstrap4.min.css" rel="stylesheet"/>
    <link href="css/jquery-ui.css" rel="stylesheet"/>
    <link href="css/jquery.dataTables.min.css" rel="stylesheet"/>
    <style rel="stylesheet">
        table.dataTable tr.odd { background-color: #E2E4FF; }
        table.dataTable tr.even { background-color: white; }
    </style>
</head>

<body>
<div style="margin-left: auto;margin-right: auto;width:50%;float: none;margin-top: 3%;">
    <table id="userDatatable" class="cell-border" style="width: 100%;">
        <thead>
        <tr>
            <th>id</th>
            <th>firstname</th>
            <th>lastname</th>
            <th>age</th>
        </tr>
        </thead>
    </table>
</div>

<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap4.min.js"></script>
<script src="js/jquery-ui.js" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript">
    var userDatatable = {};

    $(document).ready(function () {
        jQuery.fn.dataTableExt.oApi.fnSetFilteringEnterPress = function (oSettings) {
            var _that = this;

            this.each(function (i) {
                $.fn.dataTableExt.iApiIndex = i;
                var anControl = $('input', _that.fnSettings().aanFeatures.f);

                anControl.unbind('keyup search input').bind(
                    'keyup search input',
                    function (e) {
                        if (anControl.val().length == "" || anControl.val().length > 0) {
                            _that.fnFilter(anControl.val());
                        }
                    });
                return this;
            });
            return this;
        };

        userDatatable = $('#userDatatable').DataTable({
            "processing": true,
            "serverSide": true,
            "pageLength": 10,
            "ajax": {
                "url": "./select_all_users",
                "data": function (data) {
                    //process data before sent to server.
                }
            },
            "columns": [
                {"data": "id", "defaultContent": "", "id": "logger", "title": "id"},
                {"data": "firstname", "defaultContent": "", "firstname": "message", "title": "firstname"},
                {"data": "lastname", "defaultContent": "", "lastname": "logLevel", "title": "lastname"},
                {"data": "age", "defaultContent": "", "age": "exception", "title": "age"}
            ]
        });

        $('#userDatatable').dataTable().fnSetFilteringEnterPress();

    });
</script>
</body>

</html>
