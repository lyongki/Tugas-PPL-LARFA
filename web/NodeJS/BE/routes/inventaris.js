const express = require("express");
const router = express.Router();
const model = require('../models')
const Op = model.Sequelize.Op
// console.log(Op)

router.get("/",async function (req, res) {
    try {
        const inventaris = await model.inventaris.findAll()
        res.json({ data:inventaris });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.get("/:id",async function (req, res) {
    try {
        const inventaris = await model.inventaris.findOne({
            where:{
                id:req.params.id
            }
        })
        res.json({ data:inventaris });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.delete("/:id",async function (req, res) {
    try {
        const inventaris = await model.inventaris.destroy({
            where: {
                id:req.params.id
            }
        })
        res.json({ data:inventaris });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.put("/:id",async function (req, res) {
    try {
        const inventaris = await model.inventaris.update(req.body,{
            where: {
                id:req.params.id
            }
        })
        res.json({ data:inventaris[0] });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.post("/", async function (req, res) {
    try {
        const inventaris = await model.inventaris.create(req.body)
        res.json({ data:inventaris });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});

module.exports = router;
