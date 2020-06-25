const express = require("express");
const router = express.Router();
const model = require('../models')
const Op = model.Sequelize.Op
// console.log(Op)

router.get("/",async function (req, res) {
    try {
        const rapat = await model.rapat.findAll()
        res.json({ data:rapat });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.get("/:id",async function (req, res) {
    try {
        const rapat = await model.rapat.findOne({
            where:{
                id:req.params.id
            }
        })
        res.json({ data:rapat });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.delete("/:id",async function (req, res) {
    try {
        const rapat = await model.rapat.destroy({
            where: {
                id:req.params.id
            }
        })
        res.json({ data:rapat });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.put("/:id",async function (req, res) {
    try {
        const rapat = await model.rapat.update(req.body,{
            where: {
                id:req.params.id
            }
        })
        res.json({ data:rapat[0] });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.post("/", async function (req, res) {
    try {
        const rapat = await model.rapat.create(req.body)
        res.json({ data:rapat });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});

module.exports = router;
