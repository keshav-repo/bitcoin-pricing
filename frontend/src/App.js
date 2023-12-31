import './App.css';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import React, { useState, useEffect } from 'react';
import moment from 'moment';
import BitcoinPriceGraph from './component/BitcoinPriceGraph';

function App() {

  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const [currency, setCurrencyOption] = useState('');
  const [availableCurr, setAvailableCurr] = useState([]);
  const options = ['USD', 'INR', 'Option 3'];
  const [resData, setResData] = useState(null);

  const host = process.env.REACT_APP_API_HOST || 'http://localhost:3000';

  const fetchPriceStates = () => {
    var parsedStartDate = moment(startDate);
    parsedStartDate = parsedStartDate.format('YYYY-MM-DD')
    var parsedEndDate = moment(endDate);
    parsedEndDate = parsedEndDate.format('YYYY-MM-DD');
    const url = `${host}/api/bitcoin/history?startDate=${parsedStartDate}&endDate=${parsedEndDate}&currency=${currency}`;

    fetch(url)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(res => {
        console.log('response is');
        console.log(res);
        setResData(res);
      })
      .catch(error => {
        console.error('Error occurred:', error);
      });
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    fetchPriceStates();
  };

  useEffect(() => {
    // fetch currency available
    const url = `${host}/api/bitcoin/currency`;
    fetch(url)
      .then(response => response.json())
      .then(currencies => {
        var currList = [];
        currencies.map(obj => {
          currList.push(obj['currency']);
        })
        setAvailableCurr(currList);
        console.log(currList);
      })
      .catch(error => console.error('Error fetching currency:', error));
  }, []);

  useEffect(() => {
    fetchPriceStates();
  }, [currency])

  return (
    <div className="container mt-5">
      <h2>Bitcoin pricing</h2>
      <form onSubmit={handleSubmit}>
        <div className="row">
          <div className="col-md-4">
            <label>Start Date</label>
            <DatePicker
              selected={startDate}
              onChange={(date) => setStartDate(date)}
              dateFormat="yyyy-MM-dd"
              placeholderText="Select start date"
              className="form-control"
            />
          </div>
          <div className="col-md-4">
            <label>End Date</label>
            <DatePicker
              selected={endDate}
              onChange={(date) => setEndDate(date)}
              dateFormat="yyyy-MM-dd"
              placeholderText="Select end date"
              className="form-control"
            />
          </div>
          <div className="col-md-4">
            <div className="form-group d-flex">
              <label style={{ lineHeight: '38px' }}>Currency</label>
              <select
                className="form-control"
                value={currency}
                onChange={(e) => setCurrencyOption(e.target.value)}>
                <option value="">Select an option</option>
                {availableCurr.map((option, index) => (
                  <option key={index} value={option}>
                    {option}
                  </option>
                ))}
              </select>
            </div>

          </div>
        </div>

        <div className="mt-3">
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </div>
      </form>
      <>
        <h1>Stock Price Analysis</h1>
        {resData && resData.stats.map(res => (
          <p>{res['query']} : {currency}  {res['value']}</p>
        ))}
      </>
      {
        resData && (
          <BitcoinPriceGraph data={resData} />
        )
      }
    </div>
  );
}

export default App;
