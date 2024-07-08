import React from 'react'

const LoginPage = () => {

  const [formData, setFormData] = React.useState();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name] : e.target.value
    });
  };;

  const handleSubmit = (e) => {
    e.preventDefault();
  };


  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input type="text" name='id' onChange={handleChange}/>
        <input type="password" name='pw' onChange={handleChange}/>
        <button> 로그인 </button>
      </form>
    </div>
  )
}

export default LoginPage