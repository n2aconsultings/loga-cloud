import { Component } from "react";
import ReactApexChart from "react-apexcharts";

export class Chart extends Component{

    constructor(props) {
        super(props);

        this.state = {
            series: [],
            options: {},
        };
    }

    componentWillReceiveProps(props){
        this.setState({
            series: [{
                name: 'Tâches facturées (Cfa)',
                data: props.tasks.map((row)=>row.amount)
            }, {
                name: 'Pièces facturées (Cfa)',
                data: props.spares.map((row)=>row.amount)
            }],
            options: {
                chart: {
                    height: 350,
                    type: 'area'
                },
                dataLabels: {
                    enabled: false
                },
                stroke: {
                    curve: 'smooth'
                },
                xaxis: {
                    type: 'datetime',
                    categories: props.tasks.map((row)=>row.period)
                },
                tooltip: {
                    x: {
                        format: 'dd/MM/yy HH:mm'
                    },
                },
            },
        });
    }

    render() {
        return (
            <div className="container-fluid mt-5" id="chart">
                <ReactApexChart options={this.state.options} series={this.state.series} type="area" height={350} />
            </div>
        );
    }
}