'use strict';
const faker = require('faker')
module.exports = {
  up: (queryInterface, Sequelize) => {
      return queryInterface.bulkInsert('penggunas', 
        Array.from({length:10}).map(()=>({
          nama:faker.name.findName(),
          username:faker.internet.userName(),
          password:faker.internet.password(),
          role: Math.random(Math.random() * 5)
        })),
      {});
  },

  down: (queryInterface, Sequelize) => {
    /*
      Add reverting commands here.
      Return a promise to correctly handle asynchronicity.

      Example:
      return queryInterface.bulkDelete('People', null, {});
    */
  }
};
