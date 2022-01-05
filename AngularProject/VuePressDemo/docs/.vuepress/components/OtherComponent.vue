<template>
  <div>
    <code-diff :old-string="oldStr" :new-string="newStr" :outputFormat="'side-by-side'" :context="10" />
  </div>
</template>
<script>
import CodeDiff from 'vue-code-diff'
export default {
  components: {CodeDiff},
  data(){
    return {
      oldStr: '{\n' +
          '\t"method": "POST",\n' +
          '\t"protocol": "http",\n' +
          '\t"host": "qa.etowertech.com",\n' +
          '\t"port": 80,\n' +
          '\t"path": [\n' +
          '\t\t"services",\n' +
          '\t\t"shipper",\n' +
          '\t\t"orders"\n' +
          '\t],\n' +
          '\t"query": [],\n' +
          '\t"body": {\n' +
          '\t\t"type": "JSON",\n' +
          '\t\t"content": [\n' +
          '\t\t\t{\n' +
          '\t\t\t\t"referenceNo": "{{static(Test) + random(numberStr,8)}}",\n' +
          '\t\t\t\t"serviceCode": "UBI.CN2WW.UCL.STD",\n' +
          '\t\t\t\t"extendData": {\n' +
          '\t\t\t\t\t"vendorid": "IM3720002056"\n' +
          '\t\t\t\t},\n' +
          '\t\t\t\t"incoterm": "DDU",\n' +
          '\t\t\t\t"weight": "1.0",\n' +
          '\t\t\t\t"length": "16",\n' +
          '\t\t\t\t"width": "11",\n' +
          '\t\t\t\t"height": "1",\n' +
          '\t\t\t\t"weightUnit": "KG",\n' +
          '\t\t\t\t"invoiceValue": 5,\n' +
          '\t\t\t\t"invoiceCurrency": "USD",\n' +
          '\t\t\t\t"recipientName": "james doe",\n' +
          '\t\t\t\t"recipientCompany": "james doe",\n' +
          '\t\t\t\t"phone": "96655524152",\n' +
          '\t\t\t\t"email": "testc9441@gmail.com",\n' +
          '\t\t\t\t"addressLine1": "3rd cross",\n' +
          '\t\t\t\t"addressLine2": "west avenue",\n' +
          '\t\t\t\t"addressLine3": "123456",\n' +
          '\t\t\t\t"city": "Provincia de Santiago",\n' +
          '\t\t\t\t"state": "Reg",\n' +
          '\t\t\t\t"postcode": "7750000",\n' +
          '\t\t\t\t"batteryPacking": "",\n' +
          '\t\t\t\t"country": "CL",\n' +
          '\t\t\t\t"shipperName": "Tak Yuet",\n' +
          '\t\t\t\t"shipperPhone": "0920004532",\n' +
          '\t\t\t\t"shipperAddressLine1": "3rd cross",\n' +
          '\t\t\t\t"shipperAddressLine2": "west avenue",\n' +
          '\t\t\t\t"shipperAddressLine3": "123456",\n' +
          '\t\t\t\t"description": "222",\n' +
          '\t\t\t\t"shipperState": "广东省",\n' +
          '\t\t\t\t"shipperCountry": "CN",\n' +
          '\t\t\t\t"shipperCity": "SZ",\n' +
          '\t\t\t\t"recipientTaxId": "33908597803",\n' +
          '\t\t\t\t"instruction": "www.sellerwebsite.com",\n' +
          '\t\t\t\t"orderItems": [\n' +
          '\t\t\t\t\t{\n' +
          '\t\t\t\t\t\t"sku": "Battery yes X 1",\n' +
          '\t\t\t\t\t\t"description": "ASFDAS  ASFASF",\n' +
          '\t\t\t\t\t\t"hsCode": "39231090",\n' +
          '\t\t\t\t\t\t"originCountry": "USD",\n' +
          '\t\t\t\t\t\t"itemCount": "1",\n' +
          '\t\t\t\t\t\t"unitValue": 5,\n' +
          '\t\t\t\t\t\t"weight": "1.0",\n' +
          '\t\t\t\t\t\t"itemNo": ""\n' +
          '\t\t\t\t\t}\n' +
          '\t\t\t\t]\n' +
          '\t\t\t}\n' +
          '\t\t]\n' +
          '\t},\n' +
          '\t"header": [\n' +
          '\t\t{\n' +
          '\t\t\t"key": "Content-Type",\n' +
          '\t\t\t"value": "application/json",\n' +
          '\t\t\t"disabled": false\n' +
          '\t\t}\n' +
          '\t],\n' +
          '\t"preScript": [\n' +
          '\t\t{\n' +
          '\t\t\t"name": "WALL_TECH",\n' +
          '\t\t\t"param": {\n' +
          '\t\t\t\t"timeDifference": 0,\n' +
          '\t\t\t\t"key": "7AZclc8tk4MyQdE4_wGjNg",\n' +
          '\t\t\t\t"token": "testN-zHClGXv7lpT3Q1os6"\n' +
          '\t\t\t}\n' +
          '\t\t}\n' +
          '\t]\n' +
          '}',
      newStr: '{\n' +
          '\t"method": "POST",\n' +
          '\t"protocol": "http",\n' +
          '\t"host": "qa.etowertech.com",\n' +
          '\t"port": 80,\n' +
          '\t"path": [\n' +
          '\t\t"services",\n' +
          '\t\t"shipper",\n' +
          '\t\t"orders"\n' +
          '\t],\n' +
          '\t"query": [],\n' +
          '\t"body": {\n' +
          '\t\t"type": "JSON",\n' +
          '\t\t"content": [\n' +
          '\t\t\t{\n' +
          '\t\t\t\t"referenceNo": "{{static(Test) + random(numberStr,8)}}",\n' +
          '\t\t\t\t"serviceCode": "UBI.CN2WW.UCL.STD",\n' +
          '\t\t\t\t"extendData": {\n' +
          '\t\t\t\t\t"vendorid": "IM3720002056"\n' +
          '\t\t\t\t},\n' +
          '\t\t\t\t"incoterm": "DDU",\n' +
          '\t\t\t\t"weight": "1.0",\n' +
          '\t\t\t\t"length": "16",\n' +
          '\t\t\t\t"width": "11",\n' +
          '\t\t\t\t"height": "1",\n' +
          '\t\t\t\t"weightUnit": "KG",\n' +
          '\t\t\t\t"invoiceValue": 5,\n' +
          '\t\t\t\t"invoiceCurrency": "USD",\n' +
          '\t\t\t\t"recipientName": "james doe",\n' +
          '\t\t\t\t"recipientCompany": "james doe",\n' +
          '\t\t\t\t"phone": "96655524152",\n' +
          '\t\t\t\t"email": "testc9441@gmail.com",\n' +
          '\t\t\t\t"addressLine1": "3rd cross",\n' +
          '\t\t\t\t"addressLine2": "west avenue",\n' +
          '\t\t\t\t"addressLine3": "123456",\n' +
          '\t\t\t\t"city": "Provincia de Santiago",\n' +
          '\t\t\t\t"state": "Reg",\n' +
          '\t\t\t\t"postcode": "77523",\n' +
          '\t\t\t\t"batteryPacking": "",\n' +
          '\t\t\t\t"country": "CL",\n' +
          '\t\t\t\t"shipperName": "Tak Yuet",\n' +
          '\t\t\t\t"shipperPhone": "0920004532",\n' +
          '\t\t\t\t"shipperAddressLine1": "3rd cross",\n' +
          '\t\t\t\t"shipperAddressLine2": "west avenue",\n' +
          '\t\t\t\t"shipperAddressLine3": "123456",\n' +
          '\t\t\t\t"description": "222",\n' +
          '\t\t\t\t"shipperState": "广东省",\n' +
          '\t\t\t\t"shipperCountry": "CN",\n' +
          '\t\t\t\t"shipperCity": "SZ",\n' +
          '\t\t\t\t"recipientTaxId": "33908597803",\n' +
          '\t\t\t\t"instruction": "www.sellerwebsite.com",\n' +
          '\t\t\t\t"orderItems": [\n' +
          '\t\t\t\t\t{\n' +
          '\t\t\t\t\t\t"sku": "Battery yes X 1",\n' +
          '\t\t\t\t\t\t"description": "ASFDAS  ASFASF",\n' +
          '\t\t\t\t\t\t"hsCode": "39231090",\n' +
          '\t\t\t\t\t\t"originCountry": "USD",\n' +
          '\t\t\t\t\t\t"itemCount": "1",\n' +
          '\t\t\t\t\t\t"unitValue": 5,\n' +
          '\t\t\t\t\t\t"weight": "1.0",\n' +
          '\t\t\t\t\t\t"itemNo": ""\n' +
          '\t\t\t\t\t}\n' +
          '\t\t\t\t]\n' +
          '\t\t\t}\n' +
          '\t\t]\n' +
          '\t},\n' +
          '\t"header": [\n' +
          '\t\t{\n' +
          '\t\t\t"key": "Content-Type",\n' +
          '\t\t\t"value": "application/json",\n' +
          '\t\t\t"disabled": false\n' +
          '\t\t}\n' +
          '\t],\n' +
          '\t"preScript": [\n' +
          '\t\t{\n' +
          '\t\t\t"name": "WALL_TECH",\n' +
          '\t\t\t"param": {\n' +
          '\t\t\t\t"timeDifference": 0,\n' +
          '\t\t\t\t"key": "7AZclc8tk4MyQdE4_wGjNg",\n' +
          '\t\t\t\t"token": "testN-zHClGXv7lpT3Q1os6"\n' +
          '\t\t\t}\n' +
          '\t\t}\n' +
          '\t]\n' +
          '}'
    }
  }
}
</script>

<style>
.editor {
  width: 600px;
  height: 800px;
}
</style>
