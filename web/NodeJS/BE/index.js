const express = require("express");
const cors = require("cors");
const userRoute = require("./routes/user");
const eventRoute = require("./routes/event");
const inventarisRoute = require("./routes/inventaris");
const kasRoute = require("./routes/kas");
const rapatRoute = require("./routes/rapat");
const suratRoute = require("./routes/surat");
const uangRoute = require("./routes/uang");
const bodyParser = require("body-parser");
const app = express();

app.use(express.json());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());
app.use(cors());
app.use("/user", userRoute);
app.use("/event", eventRoute);
app.use("/inventaris", inventarisRoute);
app.use("/kas", kasRoute);
app.use("/rapat", rapatRoute);
app.use("/surat", suratRoute);
app.use("/uang", uangRoute);

app.listen(9000, function () {
  console.log("di run pada port 9000");
});
