const express = require("express");
const router = express.Router();
const model = require('../models')
const Op = model.Sequelize.Op
// console.log(Op)

router.get("/",async function (req, res) {
    try {
        const surat = await model.surat.findAll()
        res.json({ data:surat });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.get("/:id",async function (req, res) {
    try {
        const surat = await model.surat.findOne({
            where:{
                id:req.params.id
            }
        })
        res.json({ data:surat });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.delete("/:id",async function (req, res) {
    try {
        const surat = await model.surat.destroy({
            where: {
                id:req.params.id
            }
        })
        res.json({ data:surat });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.put("/:id",async function (req, res) {
    try {
        const surat = await model.surat.update(req.body,{
            where: {
                id:req.params.id
            }
        })
        res.json({ data:surat[0] });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.post("/", async function (req, res) {
    try {
        const surat = await model.surat.create(req.body)
        res.json({ data:surat });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});

module.exports = router;
