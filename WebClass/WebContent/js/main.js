$('#myModal').on('show.bs.modal', function(event) {
var button = $(event.relatedTarget) // Button that triggered the modal

var id = $('#id').val();
var pw = $('#pw').val();
console.log(id, pw);

var modal = $(this);

// 서버로  post 전속(ajax)
$.post("http://httpbin.org/post", {
    "id": id,
    "pw": pw
  },
  function(data) {
    modal.find('.modal-title').text(id + '님 로그인 되었습니다..');
  });

});
$('#myModal2').on('show.bs.modal', function(event) {
var button = $(event.relatedTarget) // Button that triggered the modal
console.log("하이");
var id = $('#user').val();

var modal = $(this);

// 서버로  post 전속(ajax)
$.post("http://httpbin.org/post", {
    "id": id
  },
  function(data) {
    modal.find('.modal-title').text(id + '님 회원가입되었습니다.');
  });

});
function ch(office){
  console.log("abc");
  $('#bg-office').css("background-image", "url('../images/"+office+".jpg')");
}
