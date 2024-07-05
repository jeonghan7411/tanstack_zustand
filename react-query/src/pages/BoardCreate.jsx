import React from 'react'
import { useBoardCreate, useBoardUpdate } from '../queries/useBoardQueries';

const BoardCreate = ({data,setUpdateForm}) => {

  const { mutate: createBoard, isLoading: isLoadingCreate, isSuccess: isSuccessCreate, isError: isErrorCreate } = useBoardCreate();
  const { mutate: updateBoard, isLoading: isLoadingUpdate, isSuccess: isSuccessUpdate, isError: isErrorUpdate } = useBoardUpdate();


  const [formData, setFormData] = React.useState({
    idx : data?.idx || '',
    title : data?.title ||'',
    content : data?.content || '',
    createWriter : data?.createWriter || '',
    updateWriter : data?.updateWriter || '',
  });

  React.useEffect(() => {
    setFormData({
      idx: data?.idx || '',
      title: data?.title || '',
      content: data?.content || '',
      createWriter: data?.createWriter || '',
      updateWriter : data?.updateWriter || '',
    });
  }, [data]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name] : e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (!formData.idx) {
      createBoard(formData);
    } else {
      updateBoard(formData)
    }

  };


  return (
    <div style={{marginTop:'30px'}}>
      <form onSubmit={handleSubmit}>
        <label>
          제목:
          <input type="text" name="title" value={formData.title} onChange={handleChange} />
        </label>
        <label>
          내용:
          <textarea name="content" value={formData.content} onChange={handleChange} />
        </label>
        {
          formData.idx ?
          <label> 수정자:
            <input type="text" name="updateWriter" value={formData.updateWriter} onChange={handleChange} />
          </label> :
          <label>
            작성자:
            <input type="text" name="createWriter" value={formData.createWriter} onChange={handleChange} />
          </label>
        }
        <button>등록</button>
      </form>
      {(isLoadingCreate || isLoadingUpdate) && <div>게시글을 {formData.idx ? '수정' : '작성'}하는 중...</div>}
      {(isSuccessCreate || isSuccessUpdate) && (
        <div>게시글이 성공적으로 {formData.idx ? '수정' : '작성'}되었습니다!</div>
      )}
      {(isErrorCreate || isErrorUpdate) && (
        <div>게시글 {formData.idx ? '수정' : '작성'} 중 오류가 발생했습니다: {isErrorCreate?.message || isErrorUpdate?.message}</div>
      )}
    </div>
  )
}

export default BoardCreate