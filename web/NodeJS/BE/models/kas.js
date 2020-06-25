'use strict';
module.exports = (sequelize, DataTypes) => {
  const kas = sequelize.define('kas', {
    nim: DataTypes.STRING,
    nama: DataTypes.STRING,
    tanggal: DataTypes.DATE
  }, {
    timestamps: false,
  });
  kas.associate = function(models) {
    // associations can be defined here
  };
  return kas;
};