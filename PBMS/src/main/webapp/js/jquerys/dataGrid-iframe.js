 // 解决datagrid 父页面调子页面 ,关闭子页面刷新父页面dataGrid功能
function datagrid(gridName,method){
      return $("#"+gridName).datagrid('method');
}