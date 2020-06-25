'use strict';
module.exports = (sequelize, DataTypes) => {
  const event = sequelize.define('event', {
    nama_event: DataTypes.STRING,
    tanggal: DataTypes.DATE,
    thumbnail: DataTypes.STRING,
    deskripsi: DataTypes.STRING,
    nama_ukm: DataTypes.STRING,
    evaluasi: DataTypes.TEXT('long')
  }, {
    timestamps: false
  });
  event.associate = function(models) {
    // associations can be defined here
  };
  return event;
};