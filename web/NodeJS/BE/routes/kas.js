const express = require("express");
const router = express.Router();
const model = require("../models");
const Op = model.Sequelize.Op;
// console.log(Op)

router.get("/", async function (req, res) {
  //   console.log(req.headers.authorization);
  try {
    const kas = await model.kas.findAll({
      //   where: {
      //     role: req.headers.authorization.role,
      //   },
    });
    res.json({ data: kas });
  } catch (error) {
    res.status(404);
    res.json({
      message: [],
    });
  }
});
router.get("/:id", async function (req, res) {
  try {
    const kas = await model.kas.findOne({
      where: {
        id: req.params.id,
      },
    });
    res.json({ data: kas });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});
router.delete("/:id", async function (req, res) {
  try {
    const kas = await model.kas.destroy({
      where: {
        id: req.params.id,
      },
    });
    res.json({ data: kas });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});
router.put("/:id", async function (req, res) {
  try {
    const kas = await model.kas.update(req.body, {
      where: {
        id: req.params.id,
      },
    });
    res.json({ data: kas[0] });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});
router.post("/", async function (req, res) {
  try {
    const kas = await model.kas.create(req.body);
    res.json({ data: kas });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});

module.exports = router;
