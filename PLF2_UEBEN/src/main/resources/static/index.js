let people = [];
let page = 1;
let pages = 1;

const fetchPeople = async (pageToLoad) => {
    page = pageToLoad || 1;
    const response = await fetch('/people?page=' + (page - 1));
    data = await response.json();
    pages = data.totalPages;
    people = data.content;
    renderPeople();
};

const renderPeople = () => {
    const container = document.querySelector('#people tbody');
    const html = people.reduce((result, {id, name, birthdate, hobbies}) => {
        result += `
            <tr>
                <td>
                    ${id}
                </td>
                <td>
                    ${name}
                </td>
                <td>
                    ${new Date(birthdate).toLocaleDateString()}
                </td>
                <td>
                    ${hobbies.map(h => h.name).join(', ')}
                </td>
            </tr>
        `;
        return result;
    }, '');

    container.innerHTML = html + document.querySelector('#person-create').innerHTML;

    const pageSelector = document.querySelector("#page");
    let pageHtml = '';

    for(let i = 1; i <= pages; i++) {
        pageHtml += `
            <option value="${i}" ${i === page ? 'selected' : ''}>${i}</option>
        `;
    }

    pageSelector.innerHTML = pageHtml;
};

const onCreatePerson = async () => {
    const name = document.querySelector('#name').value;
    const birthdate = document.querySelector('#birthdate').value;
    const hobbies = document.querySelector('#hobbies').value;

    await fetch('/people', {
        method: 'POST',
        body: JSON.stringify({ name, birthdate, hobbies: hobbies.split(',').map(e => e.trim())}),
        headers: {
            'Content-Type': 'application/json'
        }
    });
};

const onChangePage = ({target}) => {
    const value = +target.value;
    fetchPeople(value);
};

window.addEventListener('load', () => {
    fetchPeople();
});