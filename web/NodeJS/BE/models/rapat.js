'use strict';
module.exports = (sequelize, DataTypes) => {
  const rapat = sequelize.define('rapat', {
    tanggal: DataTypes.DATE,
    nama: DataTypes.STRING,
    hasil: DataTypes.STRING
  }, {
    timestamps: false
  });
  rapat.associate = function(models) {
    // associations can be defined here
  };
  return rapat;
};