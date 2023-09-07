import React from 'react';
import { Bar } from 'react-chartjs-2';
import Chart from 'chart.js/auto';

const BitcoinPriceGraph = ({ data }) => {
  const labels = Object.keys(data.history);
  const values = Object.values(data.history);

  const chartData = {
    labels: labels,
    datasets: [
      {
        label: 'Bitcoin Price',
        data: values,
        borderColor: 'rgba(75, 192, 192, 1)'
      },
    ],
  };

  const options = {
    scales: {
      x: {
        type: 'time',
        time: {
          unit: 'day',
        },
      },
      y: {
        beginAtZero: true,
      },
    },
  };

  return (
    <div>
      <Bar data={chartData} />
    </div>
  );
};

export default BitcoinPriceGraph;
