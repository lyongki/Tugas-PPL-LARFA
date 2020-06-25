'use strict';
module.exports = (sequelize, DataTypes) => {
  const uang = sequelize.define('uang', {
    uang: DataTypes.INTEGER,
    tanggal: DataTypes.DATE,
    role: DataTypes.INTEGER
  }, {
    timestamps: false
  });
  uang.associate = function(models) {
    // associations can be defined here
  };
  return uang;
};