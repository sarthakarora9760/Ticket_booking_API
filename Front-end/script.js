function purchase() {
  let cnf = confirm("Price to be Paid : 20$");
  if (cnf) {
    axios
      .post("http://localhost:8078/booking/book", {
        from: "London",
        to: "Paris",
        user: {
          firstName: fname.value,
          lastName: lname.value,
          email: email.value,
        },
        price: 20,
      })
      .then((response) => {
        // Handle the response
        console.log(response.data);
        alert("Ticket has been booked Successfully!");
      })
      .catch((error) => {
        // Handle errors
        console.error("Error:", error);
      });
  }
}
function viewUsers() {
  let sectioninput = prompt(
    "Enter Section ('A' or 'B') to get details of all tickets booked: "
  );
  axios
    .post("http://localhost:8078/booking/viewUsers", {
      section: sectioninput,
    })
    .then((response) => {
      // Handle the response
      let resp = response.data;
      const newDiv = document.createElement("div");
      const newContent = document.createTextNode(
        `${JSON.stringify(resp, null, 2)}`
      );
      newDiv.appendChild(newContent);
      const currentDiv = document.getElementById("data");
      document.body.insertBefore(newDiv, currentDiv);
      console.log(resp);
    })
    .catch((error) => {
      // Handle errors
      console.error("Error:", error);
    });
}
function viewReceipt() {
  let emailid = prompt("Enter an email to view Receipt: ");
  axios
    .post("http://localhost:8078/booking/details", {
      email: emailid,
    })
    .then((response) => {
      // Handle the response
      let resp = response.data;
      const newreceiptDiv = document.createElement("div");
      const newreceiptContent = document.createTextNode(
        `${JSON.stringify(resp, null, 2)}`
      );
      newreceiptDiv.appendChild(newreceiptContent);
      const oldDiv = document.getElementById("receiptdata");
      document.body.insertBefore(newreceiptDiv, oldDiv);
      console.log(resp);
    })
    .catch((error) => {
      // Handle errors
      console.error("Error:", error);
    });
}

function remove() {
  let user = prompt("Enter Email id of the user to remove: ");
  axios
    .post("http://localhost:8078/booking/removeUser", {
      email: user,
    })
    .then((response) => {
      // Handle the response
      let resp = response.data;
      if(resp=="not removed"){
        alert("Email ID not found. Enter correct Email ID.");
      }
      else{
        alert("User got removed successfully.");
      }
    })
    .catch((error) => {
      // Handle errors
      console.error("Error:", error);
    });
}
function modify() {
  let user = prompt("Enter Email id of the user to modify seat: ");
  let section=prompt("Enter section to which you want to modify the seat to: ");
  axios
    .post("http://localhost:8078/booking/modifySeat", {
      email: user,
      section: section,
    })
    .then((response) => {
      // Handle the response
      alert("Section has been modified successfully! ");
    })
    .catch((error) => {
      // Handle errors
      console.error("Error:", error);
    });
}
