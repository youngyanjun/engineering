const fs = require('fs');
const path = require('path');

module.exports = (app) => {
  app.use(/\/api/, (req, res) => {
    const jsonPath = `${path.join(__dirname, req.path.replace(/^\/api/, ''))}.json`;
    res.send(fs.readFileSync(jsonPath, 'utf8'));
  });
};
