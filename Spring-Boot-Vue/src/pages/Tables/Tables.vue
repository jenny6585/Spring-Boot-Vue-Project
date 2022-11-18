<template>
  <div class="tables-basic">
    <b-breadcrumb>
      <b-breadcrumb-item>YOU ARE HERE</b-breadcrumb-item>
      <b-breadcrumb-item active>Tables Basic</b-breadcrumb-item>
    </b-breadcrumb>
    <h2 class="page-title">Tables - <span class="fw-semi-bold">Static</span></h2>
    <b-row>
      <b-col lg="6">
        <!-- Stripped Table start -->
        <Widget
          title="<h5>Table <span class='fw-semi-bold'>Styles</span></h5>"
          customHeader settings close
        > <!-- hover table start -->
          <h3>Hover <span class="fw-semi-bold">Table</span></h3>
          <p>Trace only what's really important. <code>.table-hover</code> is made for it.</p>
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>No.</th>
                  <th>Title</th>
                  <th>Name</th>
                  <th>Date</th>
                  <th>View</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td><a href="#">Mark</a></td>
                  <td>Otto</td>
                  <td>ottoto@example.com</td>
                  <td><b-badge variant="gray" pill>Pending</b-badge></td>
                </tr>
                <tr>
                  <td>2</td>
                  <td><a href="#">Mark</a></td>
                  <td>Thornton</td>
                  <td><a href="#">fat.thor@example.com</a></td>
                  <td><b-badge variant="gray" pill>Unconfirmed</b-badge></td>
                </tr>
                <tr>
                  <td>3</td>
                  <td><a href="#">Mark</a></td>
                  <td>the Bird</td>
                  <td><a href="#">larry@example.com</a></td>
                  <td><b-badge variant="primary" pill>New</b-badge></td>
                </tr>
                <tr>
                  <td>4</td>
                  <td><a href="#">Mark</a></td>
                  <td>Horadnia</td>
                  <td>peter@example.com</td>
                  <td><b-badge variant="success" pill>Active</b-badge></td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- hover table end -->
        </Widget>
        <!-- Stripped Table start -->
      </b-col>
      <b-col lg="6">
          <Widget
            title="<h5>Table <span class='fw-semi-bold'>Styles</span></h5>"
            customHeader ettings close
          >
            <h3>Bordered <span class="fw-semi-bold">Table</span></h3>
            <p>Each row is highlighted. You will never lost there. That&apos;s how
              all of us learned in school the table should look like. Just add
              <code>.table-bordered</code> to it.</p>
            <table class="table table-bordered table-lg mt-lg mb-0">
              <thead class="text-uppercase">
                <tr>
                  <th>
                    <div class="abc-checkbox">
                      <input type="checkbox"
                        id="checkbox10" :checked="checkboxes2[0]"
                        @change="event => checkAll(event, 'checkboxes2')"
                      />
                      <label for="checkbox10" />
                    </div>
                  </th>
                  <th>Product</th>
                  <th class="text-right">Price</th>
                  <th class="text-center">State</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>
                    <div class="abc-checkbox">
                      <input type="checkbox"
                        id="checkbox11" :checked="checkboxes2[1]"
                        @change="event => changeCheck(event, 'checkboxes2', 1)"
                      />
                      <label for="checkbox11" />
                    </div>
                  </td>
                  <td>On the Road</td>
                  <td class="text-right">$25 224.2</td>
                  <td class="text-center">
                    <b-badge variant="success">Online</b-badge>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="abc-checkbox">
                      <input type="checkbox"
                        id="checkbox12" :checked="checkboxes2[2]"
                        @change="event => changeCheck(event, 'checkboxes2', 2)"
                      />
                      <label for="checkbox12" />
                    </div>
                  </td>
                  <td>HP Core i7</td>
                  <td class="text-right">$87 346.1</td>
                  <td class="text-center">
                    <b-badge variant="warning" class="text-gray-dark">ALERT!</b-badge>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="abc-checkbox">
                      <input type="checkbox"
                        id="checkbox13" :checked="checkboxes2[3]"
                        @change="event => changeCheck(event, 'checkboxes2', 3)"
                      />
                      <label for="checkbox13" />
                    </div>
                  </td>
                  <td>Let&apos;s Dance</td>
                  <td class="text-right">$57 944.6</td>
                  <td class="text-center">
                    <b-badge variant="danger">Construct</b-badge>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="abc-checkbox">
                      <input type="checkbox"
                        id="checkbox14" :checked="checkboxes2[4]"
                        @change="event => changeCheck(event, 'checkboxes2', 4)"
                      />
                      <label for="checkbox14" />
                    </div>
                  </td>
                  <td>Air Pro</td>
                  <td class="text-right">$118 533.1</td>
                  <td class="text-center">
                    <b-badge variant="gray">Away</b-badge>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="abc-checkbox">
                      <input type="checkbox"
                        id="checkbox15" :checked="checkboxes2[5]"
                        @change="event => changeCheck(event, 'checkboxes2', 5)"
                      />
                      <label for="checkbox15" />
                    </div>
                  </td>
                  <td>Version Control</td>
                  <td class="text-right">$72 854.5</td>
                  <td class="text-center">
                    <b-badge variant="gray">Away</b-badge>
                  </td>
                </tr>
              </tbody>
            </table>
          </Widget>
      </b-col>
    </b-row>
    
  </div>
</template>

<script>
import Vue from 'vue';
import Widget from '@/components/Widget/Widget';
import Sparklines from '../../components/Sparklines/Sparklines'

export default {
  name: 'Tables',
  components: { Widget, Sparklines },
  data() {
    return {
      tableStyles: [
        {
          id: 1,
          picture: require('../../assets/tables/1.jpg'), // eslint-disable-line global-require
          description: 'Palo Alto',
          info: {
            type: 'JPEG',
            dimensions: '200x150',
          },
          date: new Date('September 14, 2018'),
          size: '45.6 KB',
          progress: {
            percent: 29,
            colorClass: 'success',
          },
        },
        {
          id: 2,
          picture: require('../../assets/tables/2.jpg'), // eslint-disable-line global-require
          description: 'The Sky',
          info: {
            type: 'PSD',
            dimensions: '2400x1455',
          },
          date: new Date('November 14, 2018'),
          size: '15.3 MB',
          progress: {
            percent: 33,
            colorClass: 'warning',
          },
        },
        {
          id: 3,
          picture: require('../../assets/tables/3.jpg'), // eslint-disable-line global-require
          description: 'Down the road',
          label: {
            colorClass: 'danger',
            text: 'INFO!',
          },
          info: {
            type: 'JPEG',
            dimensions: '200x150',
          },
          date: new Date('September 14, 2018'),
          size: '49.0 KB',
          progress: {
            percent: 38,
            colorClass: 'inverse',
          },
        },
        {
          id: 4,
          picture: require('../../assets/tables/4.jpg'), // eslint-disable-line global-require
          description: 'The Edge',
          info: {
            type: 'PNG',
            dimensions: '210x160',
          },
          date: new Date('September 15, 2018'),
          size: '69.1 KB',
          progress: {
            percent: 17,
            colorClass: 'danger',
          },
        },
        {
          id: 5,
          picture: require('../../assets/tables/5.jpg'), // eslint-disable-line global-require
          description: 'Fortress',
          info: {
            type: 'JPEG',
            dimensions: '1452x1320',
          },
          date: new Date('October 1, 2018'),
          size: '2.3 MB',
          progress: {
            percent: 41,
            colorClass: 'primary',
          },
        },
      ],
      checkboxes1: [false, false, false, false],
      checkboxes2: [false, false, false, false, false, false],
      checkboxes3: [false, false, false, false, false, false],
    };
  },
  methods: {
    parseDate(date) {
      const dateSet = date.toDateString().split(' ');
      return `${date.toLocaleString('en-us', { month: 'long' })} ${dateSet[2]}, ${dateSet[3]}`;
    },
    checkAll(ev, checkbox) {
      const checkboxArr = (new Array(this[checkbox].length)).fill(ev.target.checked);
      Vue.set(this, checkbox, checkboxArr);
    },
    changeCheck(ev, checkbox, id) {
      this[checkbox][id] = ev.target.checked;
      if (!ev.target.checked) {
        this[checkbox][0] = false;
      }
    },
    getRandomData() {
      const result = [];

      for (let i = 0; i <= 8; i += 1) {
        result.push(Math.floor(Math.random() * 20) + 1);
      }

      return [{data: result}];
    },
    getRandomColor() {
      const colors = ['#547fff', '#9964e3', '#f55d5d', '#ffc247', '#3abf94'];
      return {colors: [colors[Math.floor(Math.random() * colors.length)]]}
    }
  },
};
</script>

<style src="./Tables.scss" lang="scss" scoped />
