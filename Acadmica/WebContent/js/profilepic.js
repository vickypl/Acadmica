const imgDiv = document.querySelector('.picoption');
const img = document.querySelector('#photo');
const file = document.querySelector('#file');
const uploadBtn = document.querySelector('#uploadBtn');

imgDiv.addEventListener('mouseenter', function(){
    uploadBtn.style.display = "block";
});

imgDiv.addEventListener('mouseleave', function(){
    uploadBtn.style.display = "none";
});

file.addEventListener('change', function(){
	
    const choosedFile = this.files[0];

    if (choosedFile) {

        const reader = new FileReader(); 

        reader.addEventListener('load', function(){
            img.setAttribute('src', reader.result);
        });

        reader.readAsDataURL(choosedFile);

    }
});

var dpsubmit= document.getElementById('uploadBtn');

function clickPhotoSubmit() {
    document.getElementById("photoSubmit").click();
}


var preloader = document.getElementById('loading');
function myLoader() {
    preloader.style.display = 'none';
}

$("#menu-toggle").click(function (e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});

//home page scripts

function openFacultyLoginForm() {
    document.getElementById("popupFacultyForm").style.display = "block";
  }
  function closeFacultyLoginForm() {
    document.getElementById("popupFacultyForm").style.display = "none";
  }
  function openStudentLoginForm() {
    document.getElementById("popupStudentForm").style.display = "block";
  }
  function closeStudentLoginForm() {
    document.getElementById("popupStudentForm").style.display = "none";
  }
  function openForm() {
    document.getElementById("popupForm").style.display = "block";
  }
  function closeForm() {
    document.getElementById("popupForm").style.display = "none";
  }
  
  //student signup forms scripts
  
  function openform1() {
      document.getElementById("form1").style.display = "block";
    }
    function closeform1() {
      document.getElementById("form1").style.display = "none";
    }
    function openform2() {
      //closeform1();
      document.getElementById("form2").style.display = "block";
    }
    function closeform2() {
      document.getElementById("form2").style.display = "none";
    }
    function openform3() {
      //closeform2();
      document.getElementById("form3").style.display = "block";
    }
    function closeform3() {
      document.getElementById("form3").style.display = "none";
    }
    function openform4() {
      //closeform3();
      document.getElementById("form4").style.display = "block";
    }
    function closeform4() {
      document.getElementById("form4").style.display = "none";
    }
    function openFacultyLoginForm() {
      document.getElementById("popupFacultyForm").style.display = "block";
    }
    function closeFacultyLoginForm() {
      document.getElementById("popupFacultyForm").style.display = "none";
    }
    function openStudentLoginForm() {
      document.getElementById("popupStudentForm").style.display = "block";
    }
    function closeStudentLoginForm() {
      document.getElementById("popupStudentForm").style.display = "none";
    }
    function openForm() {
      document.getElementById("popupForm").style.display = "block";
    }
    function closeForm() {
      document.getElementById("popupForm").style.display = "none";
    }
    function myFunction() {
    	  var x = document.getElementById("notibox");
    	  x.style.display = "none";
    }

    //faculty signup form scripts
    
    //full screen setup
   var goFullScreen = document.getElementById("goFullScreen");
    goFullScreen.addEventListener("click", function() {
        document.body.requestFullscreen();
    }, false);
    