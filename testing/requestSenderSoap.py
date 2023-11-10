import requests

def hellotest():
    soap_action = "http://services.wbdsoap/HelloService/hello"
    url = "http://localhost:8080/hello"
    headers = {
        "Content-Type": "text/xml;charset=UTF-8",
        "SOAPAction": f'"{soap_action}"',
    }
    body = """
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://services.wbdsoap/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:hello>
            <message>Some Message</message>
        </tns:hello>
    </soapenv:Body>
    </soapenv:Envelope>
    """
    response = requests.post(url, headers=headers, data=body)
    print(response.content)

def inserttest():
    soap_action = "http://services.wbdsoap/TestService/insertData"
    url = "http://localhost:8080/test"
    headers = {
        "Content-Type": "text/xml;charset=UTF-8",
        "SOAPAction": f'"{soap_action}"',
    }
    body = """
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://services.wbdsoap/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:insertData>
            <description>Some Message</description>
        </tns:insertData>
    </soapenv:Body>
    </soapenv:Envelope>
    """
    response = requests.post(url, headers=headers, data=body)
    print(response.content)

if __name__ == "__main__":
    inserttest()