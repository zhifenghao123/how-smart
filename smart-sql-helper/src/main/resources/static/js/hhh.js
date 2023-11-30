let ip_port = "127.0.0.1:8080";
$(function(){
  // do nothing
  //fillDefaultForMysql();
  //fillDefaultForGP();
  $.get('./js/config.json', function(data) {
    ip_port = data.ip + ":" + data.port;
    console.log(ip_port)
  }, 'json');

})

// function fillDefaultForMysql() {
//   $('#databaseType').val("MYSQL");
//   $("#host").val("127.0.0.1");
//   $("#port").val("3306");
//   $("#userName").val("root");
//   $("#password").val("root");
//   $("#database").val("h_order");
//   $("#inputSql").val("select * from product;");
// }

function formatSQL(event) {
  event.preventDefault();
  const input = $('#inputSql').val();
  const formattedSql = sqlFormatter.format(input, {indent: "        "});
  //alert(formattedSql)
  //console.log(formattedSql)
  // $('#formatSql').style = "white-space: pre; border: 1px solid black;";
  $('#formatSql').text(formattedSql);
}

function executeSql(){
  databaseType = $("#databaseType").val();
  host=$("#host").val();
  port=$("#port").val();
  userName = $("#userName").val();
  password=$("#password").val();
  database=$("#database").val();
  sql=$("#inputSql").val();

  submitSql(databaseType, host, port, userName, password, database, sql);

}

function submitSql(databaseType, host, port, userName, password, database, sql){
  $("#sqlResult").empty();

  var postData = {
    "databaseType":databaseType,
    "host":host,
    "port":port,
    "userName":userName,
    "password":password,
    "database":database,
    "sql":sql
  };


  $.ajax({
    url: "http://" + ip_port + "/sql/manage/executeSql",
    type:"POST",
    data: JSON.stringify(postData),
    contentType:"application/json; charset=utf-8",
    dataType:"json",
    success: function(respData){
      postProcessAfterSuc(respData);
    } })
}

function postProcessAfterSuc(respData) {
  let columnMetaDatas = respData.result.columnMetaData;
  let datas = respData.result.data;


  // 获取table控件
  const table = document.querySelector('table');
  // 使用循环将数据添加进table中
  // 创建tr节点
  const headerTr = document.createElement('tr');
  // 将tr添加到table中
  table.appendChild(headerTr);

  for (let i = 0; i < columnMetaDatas.length; i++) {
    var headerTd = document.createElement('td');
    headerTd.innerHTML = '' + columnMetaDatas[i].columnName + '';
    //headerTd.style="display: none;";
    headerTd.style = "width: auto";
    headerTr.appendChild(headerTd);
  }

  for (let j = 0; j < datas.length; j++) {
    // 创建tr节点
    const dataTr = document.createElement('tr');
    // 将tr添加到table中
    table.appendChild(dataTr);
    for (let i = 0; i < columnMetaDatas.length; i++) {
      var dataTd = document.createElement('td');
      dataTd.innerHTML =  datas[j][columnMetaDatas[i].columnName];
      dataTd.style = "width: auto";
      dataTr.appendChild(dataTd);
    }
  }
}
