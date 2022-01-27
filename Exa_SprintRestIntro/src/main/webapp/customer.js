const URL = './customer';

const getCustomer = async (id) => {
    const response = await fetch(`${URL}/${id}`);
};

const postCustomer = async (id) => {

};

const putCustomer = async (id) => {

};

const patchCustomer = async (id) => {

};

const deleteCustomer = async (id) => {

};

const showInfo = (status, method) => {
  document.getElementById('info').innerHTML = `HTTP-Method: ${method} <br/>HTTP-Status:${status}`;
};