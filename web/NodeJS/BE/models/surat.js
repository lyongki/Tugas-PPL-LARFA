'use strict';
module.exports = (sequelize, DataTypes) => {
  const surat = sequelize.define('surat', {
    tanggal: DataTypes.DATE,
    role: DataTypes.INTEGER,
    nama: DataTypes.STRING,
    file: DataTypes.STRING
  }, {
    timestamps: false
  });
  surat.associate = function(models) {
    // associations can be defined here
  };
  return surat;
};