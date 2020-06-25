const express = require("express");
const router = express.Router();
const model = require('../models');
const { sequelize } = require("../models");
const Op = model.Sequelize.Op
// console.log(Op)

router.get("/",async function (req, res) {
    try {
        const uang = await model.uang.findAll()
        res.json({ data:uang });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.get("/:id",async function (req, res) {
    try {
        const uang = await model.uang.findOne({
            where:{
                id:req.params.id
            }
        })
        res.json({ data:uang });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.delete("/:id",async function (req, res) {
    try {
        const uang = await model.uang.destroy({
            where: {
                id:req.params.id
            }
        })
        res.json({ data:uang });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.put("/:id",async function (req, res) {
    try {
        const uang = await model.uang.update(req.body,{
            where: {
                id:req.params.id
            }
        })
        res.json({ data:uang[0] });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.post("/", async function (req, res) {
    try {
        const uang = await model.uang.create(req.body)
        res.json({ data:uang });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});
router.post("/count", async function (req, res) {
    try {
        const uang = await model.uang.findAll({
            attributes:[
                'role',
                [sequelize.fn('sum',sequelize.col('uang')), 'total_uang']
            ],
            where:{

            },
            group: ['role'],
            order: sequelize.literal('role ASC')
        })
        console.log(uang)
        res.json({ data:uang });
    } catch (error) {
        res.status(404)
        res.json({
            message:error
        })
    }
});

module.exports = router;
