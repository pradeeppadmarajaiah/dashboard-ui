// when button is clicked by the user, OS puts the message in message queue and tells
    // the thread to wake up, the thread wakes up pikcs the message from the queue and
    // calls event handler (loadmore is the event handler of the button click)

class BooksServiceProxy {
    getBooksAsync(sk, tk)  {

        return new Promise( ( resolve, reject) => {
            var xhr = new XMLHttpRequest() ;

            let url = `/companies` ;

            xhr.open( "get", url , true) ;

            xhr.onload = function() {
                 resolve ( JSON.parse ( xhr.responseText ) )  ;
            };

            xhr.onerror = function() {
                reject ( "reason" ) ;
            };

            xhr.ontimeout = function() {
            };

            xhr.send() ; // async request
        } ) ;

    }
}

    var skip = 3, take = 3 ;

    function refresh() { // main thread of the browser will be used to call this event handler

        let proxy = new BooksServiceProxy() ;

        document.getElementById("busy").style.display = "block";
        document.getElementById("loadmore").style.display = "none";
        
        proxy.getBooksAsync(skip, take)
             .then ( companies => {
                for ( let company of companies ) {
                     let str = "<tr>" ;
                     str += "<td>" + company.name + "</td>" ;
                     str += "<td>" + company.author + "</td>" ;
                     str += "</tr>" ;
                     $("#bookstable").append ( str ) ;
                     skip += 3 ;

                     document.getElementById("busy").style.display = "none";
                     document.getElementById("loadmore").style.display = "block";
                 }
             })
             .catch ( ex => {

             })
//        let xhr = new window.XMLHttpRequest() ; // main thread
//
//        let url = `/books?skip=${skip}&take=${take}` ;
//
//        xhr.open ( "get", url, true /* async */ ) ; // main thread
//
//        // register a callback function to receive response from the OS
//        xhr.onload = function() {
//
//            let jsonstring = xhr.responseText ;
//
//            let books = JSON.parse ( jsonstring ) ;  // deserializing JSON string to JS objects in the RAM
//
//            for ( let book of books ) {
//                let str = "<tr>" ;
//                str += "<td>" + book.title + "</td>" ;
//                str += "<td>" + book.author + "</td>" ;
//                str += "</tr>" ;
//
//                $("#bookstable").append ( str ) ;
//            }
//
//            skip += 3 ;
//
//            document.getElementById("busy").style.display = "none";
//            document.getElementById("loadmore").style.display = "block";

        } ;

        xhr.send() ; // main thread calls send(), send() sends request OS and returns the main thread
        // OS now will send request to the server using its own thread not the browser's thread

        document.getElementById("busy").style.display = "block";
        document.getElementById("loadmore").style.display = "none";

    } // main thread can return to message event without thre response from the server