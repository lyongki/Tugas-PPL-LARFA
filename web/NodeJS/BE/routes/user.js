const express = require("express");
const model = require("../models");
const jwt = require("jsonwebtoken");
var md5 = require("md5");

const router = express.Router();
const Op = model.Sequelize.Op;

router.post("/login", async function (req, res) {
  try {
    console.log(req.body);
    const pengguna = await model.pengguna.findOne({
      where: {
        [Op.and]: {
          username: req.body.username,
          password: md5(req.body.password),
        },
      },
    });
    console.log(pengguna);
    if (!pengguna) {
      res.status(400);
      res.json({
        message: "username and password not found",
      });
    }
    const token = jwt.sign(
      {
        data: pengguna,
      },
      "tokenumkmku",
      { expiresIn: "2d" }
    );
    res.json({
      data: pengguna,
      token: token,
    });
  } catch (error) {
    console.log(error);
    res.status(400);
    res.json({ message: error });
  }
});
router.post("/register", async function (req, res) {
  try {
    const newAccount = await model.pengguna.create({
      ...req.body,
      password: md5(req.body.password),
    });
    res.json(newAccount);
  } catch (e) {
    res.status(404);
    res.json({
      message: e,
    });
  }
});
router.get("/", async function (req, res) {
  try {
    const pengguna = await model.pengguna.findAll();
    res.json({ data: pengguna });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});
router.get("/:id", async function (req, res) {
  try {
    const pengguna = await model.pengguna.findOne({
      where: {
        id: req.params.id,
      },
    });
    res.json({ data: pengguna });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});
router.delete("/:id", async function (req, res) {
  try {
    const pengguna = await model.pengguna.destroy({
      where: {
        id: req.params.id,
      },
    });
    res.json({ data: pengguna });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});
router.put("/:id", async function (req, res) {
  try {
    const pengguna = await model.pengguna.update(req.body, {
      where: {
        id: req.params.id,
      },
    });
    res.json({ data: pengguna[0] });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});

module.exports = router;
