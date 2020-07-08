const express = require("express");
const router = express.Router();
const model = require("../models");
const auth = require("../middleware/auth");
const Op = model.Sequelize.Op;
// console.log(Op)

router.get(
  "/",
  // (req, res, next) => {
  //   req.roleId = decode(req.headers.authorization).roleId ===
  //   next();
  // },
  async function (req, res) {
    try {
      const events = await model.event.findAll({
        // where: {
        //   roleId: req.roleId,
        // },
      });
      res.json({ data: events });
    } catch (error) {
      res.status(404);
      res.json({
        message: error,
      });
    }
  }
);
router.delete("/:id", async function (req, res) {
  try {
    const event = await model.event.destroy({
      where: {
        id: req.params.id,
      },
    });
    res.json({ data: event });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});
router.put("/:id", async function (req, res) {
  try {
    const event = await model.event.update(req.body, {
      where: {
        id: req.params.id,
      },
    });
    res.json({ data: event[0] });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});
router.post("/", async function (req, res) {
  try {
    const event = await model.event.create(req.body);
    res.json({ data: event });
  } catch (error) {
    res.status(404);
    res.json({
      message: error,
    });
  }
});

module.exports = router;
