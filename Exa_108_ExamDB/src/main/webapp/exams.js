let subjects = [];
let classes = [];

let currentClass;
let currentPage = 0;

window.addEventListener('load', async () => {
    await loadSubjects();
    currentClass = await loadClasses();
    await loadStudentsForClass(currentClass.classId, currentPage);
});

const loadClasses = async () => {
    classes = (await axios.get(`/classnames`)).data;
    const html = classes.reduce(((result, classname) => `${result}<option value="${classname.classId}">${classname.classname}</option>`), '');
    window['classes'].innerHTML = html;
    return classes[0];
};

const loadSubjects = async () => {
    subjects = (await axios.get('/subjects')).data;
};

const loadStudentsForClass = async (classId, page = 0) => {
    currentPage = page;
    const students = (await axios.get(`/students?classId=${classId}&page=${page}`)).data;
    const pages = (await axios.get(`/students/pages?classId=${classId}&page=${page}`)).data;
    let pageHtml = '';

    for (let i = 0; i < pages; i++) {
        pageHtml += `<option value="${i}" ${page === i ? 'selected="selected"' : ''} onclick="loadStudentsForClass(${classId}, ${i})">${i + 1}</option>`;
    }

    window['pages'].innerHTML = pageHtml;

    const html = students.reduce((result, student) =>
        result + `
            <tr>
                <th>${student.firstname}</th>
                <th>${student.lastname}</th>
                <th><button onclick="onLoadExams(${student.studentId}, '${student.firstname}', '${student.lastname}')">Load exams</button></th>
            </tr>
        `
    , '');

    window['students'].innerHTML = html;
};

const getSubjectsOptionsWithSelected = (selected) =>
      subjects.reduce(((result, subject) => `${result}<option value="${subject.subjectId}" ${subject.subjectId === selected ? 'selected="selected"' : ''}>${subject.longname}</option>`), '');


const onLoadExams = async (studentId, firstname, lastname) => {
    const exams = (await axios.get(`/exams/${studentId}`)).data;
    const html = exams.reduce((result, exam) =>
        result + `
            <tr>
                <th><input id="exam_${exam.examId}_date" type="text" value="${exam.dateOfExam}" /></th>
                <th><input id="exam_${exam.examId}_duration" type="text" value="${exam.duration}" /></th>
                <th>
                    <select id="exam_${exam.examId}_subject">
                        ${getSubjectsOptionsWithSelected(exam.subject.subjectId)}
                    </select>
                </th>
                <th>
                    <button onclick="onUpdateExam(${exam.examId}, ${studentId})">Update</button>
                    <button onclick="onDeleteExam(${exam.examId}, ${studentId})">Delete</button>
                </th>
            </tr>
        `,
    '') + `
        <tr>
            <th><input id="new_exam_date" type="text" value="" /></th>
            <th><input id="new_exam_duration" type="text" value="" /></th>
            <th>
                <select id="new_exam_subject">
                    ${getSubjectsOptionsWithSelected(subjects[0].subjectId)}
                </select>
            </th>
            <th>
                <button onclick="onCreateExam(${studentId})">Create</button>
            </th>
        </tr>
    `;

    window['examsFor'].innerHTML = `${firstname} ${lastname}`;
    window['studentExams'].innerHTML = html;
    window['exams'].style.display = 'block';
};

const onLoadClass = async () => {
    const selectedId = window['classes'].value;
    await loadStudentsForClass(selectedId, 0, true);
    currentClass = classes.find(c => c.classId === +selectedId);
    window['exams'].style.display = 'none';
};

const onUpdateExam = async (id, studentId) => {
    const request = {
        id,
        dateOfExam: window[`exam_${id}_date`].value,
        duration: +window[`exam_${id}_duration`].value,
        subjectId: +window[`exam_${id}_subject`].value
    };

    await axios.patch(`/exams/${studentId}`, request);
    await loadStudentsForClass(currentClass.classId);
    await onLoadExams(studentId);
};

const onCreateExam = async (studentId) => {
    const request = {
        dateOfExam: window[`new_exam_date`].value,
        duration: +window[`new_exam_duration`].value,
        subjectId: +window[`new_exam_subject`].value
    };

    await axios.post(`/exams/${studentId}`, request);
    await loadStudentsForClass(currentClass.classId);
    await onLoadExams(studentId);
};

const onDeleteExam = async (examId, studentId) => {
    await axios.delete(`/exams/${studentId}/${examId}`);
    await loadStudentsForClass(currentClass.classId);
    await onLoadExams(studentId);
};