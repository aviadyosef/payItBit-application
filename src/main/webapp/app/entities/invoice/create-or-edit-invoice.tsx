import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

export const CreateOrEditInvoice = props => (
  <div className="card card-box p-4 justify-content-center font-face-raleway-green">
    <div className="card-body"></div>
  </div>

  // <div class="card card-box p-4">
  //   <div class="card-body">
  //     <form id="addformid" method="post">
  //       <input type="hidden" value="14ATsZiANcu3uyRS8scFDmJUFpXaciQ8o9" name="btcwallet">
  //         <input type="hidden" value="0x9Bfb1350C77B6797fb5605f6F477d88c8a9b5434" name="usdtwallet">
  //           <div class="row mb-4 addinvoice" id="addinvoice">
  //             <div class="col-lg-6 col-md-6">
  //               <h5 class="card-title">Supplier Info</h5>
  //               <div class="form-group">
  //                 <div class="bg-gradient-border"></div>
  //               </div>
  //               <div class="row">
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Company Name" name="companyname[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Number" name="number[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-12">
  //                   <div class="form-group">
  //                     <input type="text" name="Address[]" placeholder="Address" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Website" name="website[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <input type="hidden" name="updatedid" value="88">
  //               </div>
  //             </div>
  //             <div class="col-lg-6 col-md-6">
  //               <h5 class="card-title">Invoice Info</h5>
  //               <div class="form-group">
  //                 <div class="bg-gradient-border"></div>
  //               </div>
  //               <div class="row">
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Amount" name="Amount[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <select class="form-control select-arrow" name="Currency[]">
  //                       <option value="">Select Currency</option>
  //                       <option value="1">EURO</option>
  //                     </select>
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Invoice Number" name="invoice_number[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <div class="custom-file">
  //                       <input type="file" class="custom-file-input form-control" name="uploadinvoice[]">
  //                         <label class="custom-file-label" for="customFile">Upload Invoice</label>
  //                     </div>
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-8">
  //                   <div class="form-group">
  //                     <label><b>Optional For Transfer Above 10,000 euro</b></label>
  //                     <div class="custom-file">
  //                       <input type="file" class="custom-file-input form-control" name="uploadagreement[]">
  //                         <label class="custom-file-label" for="customFile">Upload Agreement</label>
  //                     </div>
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-12">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Service Description" name="service_description[]" class="form-control">
  //                   </div>
  //                 </div>
  //               </div>
  //             </div>
  //             <div class="col-lg-6 col-md-6">
  //               <h5 class="card-title">Bank Info</h5>
  //               <div class="form-group">
  //                 <div class="bg-gradient-border"></div>
  //               </div>
  //               <div class="row">
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Account holders name" name="account_holder_name[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Account numer" name="account_number[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Iban number" name="iban_number[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="SWIFT code" name="swift_code[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-12">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Bank Address" rows="4" name="bankaddress[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <select name="country_of_origin[]" class="form-control select-arrow" onchange="Checkcountry(this,1)">
  //                       <option value="">Select Country</option>
  //                       <option value="Afghanistan">Afghanistan</option><option value="Albania">Albania</option><option value="Algeria">Algeria</option><option value="American Samoa">American Samoa</option><option value="Andorra">Andorra</option><option value="Angola">Angola</option><option value="Anguilla">Anguilla</option><option value="Antarctica">Antarctica</option><option value="Antigua And Barbuda">Antigua And Barbuda</option><option value="Argentina">Argentina</option><option value="Armenia">Armenia</option><option value="Aruba">Aruba</option><option value="Australia">Australia</option><option value="Austria">Austria</option><option value="Azerbaijan">Azerbaijan</option><option value="Bahamas">Bahamas</option><option value="Bahrain">Bahrain</option><option value="Bangladesh">Bangladesh</option><option value="Barbados">Barbados</option><option value="Belarus">Belarus</option><option value="Belgium">Belgium</option><option value="Belize">Belize</option><option value="Benin">Benin</option><option value="Bermuda">Bermuda</option><option value="Bhutan">Bhutan</option><option value="Bolivia">Bolivia</option><option value="Bosnia And Herzegowina">Bosnia And Herzegowina</option><option value="Botswana">Botswana</option><option value="Brazil">Brazil</option><option value="Brunei Darussalam">Brunei Darussalam</option><option value="Bulgaria">Bulgaria</option><option value="Burkina Faso">Burkina Faso</option><option value="Burundi">Burundi</option><option value="Cambodia">Cambodia</option><option value="Cameroon">Cameroon</option><option value="Canada">Canada</option><option value="Cape Verde">Cape Verde</option><option value="Cayman Islands">Cayman Islands</option><option value="Central African Republic">Central African Republic</option><option value="Chad">Chad</option><option value="Chile">Chile</option><option value="China">China</option><option value="Christmas Island">Christmas Island</option><option value="Cocos (Keeling) Islands">Cocos (Keeling) Islands</option><option value="Colombia">Colombia</option><option value="Comoros">Comoros</option><option value="Democratic Republic of the Congo (Kinshasa)">Democratic Republic of the Congo (Kinshasa)</option><option value="Congo, Republic of (Brazzaville)">Congo, Republic of (Brazzaville)</option><option value="Cook Islands">Cook Islands</option><option value="Costa Rica">Costa Rica</option><option value="Cote D'Ivoire">Cote D'Ivoire</option><option value="Croatia (Local Name: Hrvatska)">Croatia (Local Name: Hrvatska)</option><option value="Cuba">Cuba</option><option value="Cyprus">Cyprus</option><option value="Czech Republic">Czech Republic</option><option value="Denmark">Denmark</option><option value="Djibouti">Djibouti</option><option value="Dominica">Dominica</option><option value="Dominican Republic">Dominican Republic</option><option value="East Timor (Timor-Leste)">East Timor (Timor-Leste)</option><option value="Ecuador">Ecuador</option><option value="Egypt">Egypt</option><option value="El Salvador">El Salvador</option><option value="Equatorial Guinea">Equatorial Guinea</option><option value="Eritrea">Eritrea</option><option value="Estonia">Estonia</option><option value="Ethiopia">Ethiopia</option><option value="Falkland Islands (Malvinas)">Falkland Islands (Malvinas)</option><option value="Faroe Islands">Faroe Islands</option><option value="Fiji">Fiji</option><option value="Finland">Finland</option><option value="France">France</option><option value="French Guiana">French Guiana</option><option value="French Polynesia">French Polynesia</option><option value="French Southern Territories">French Southern Territories</option><option value="Gabon">Gabon</option><option value="Gambia">Gambia</option><option value="Georgia">Georgia</option><option value="Germany">Germany</option><option value="Ghana">Ghana</option><option value="Gibraltar">Gibraltar</option><option value="Greece">Greece</option><option value="Greenland">Greenland</option><option value="Grenada">Grenada</option><option value="Guadeloupe">Guadeloupe</option><option value="Guam">Guam</option><option value="Guatemala">Guatemala</option><option value="Guinea">Guinea</option><option value="Guinea-Bissau">Guinea-Bissau</option><option value="Guyana">Guyana</option><option value="Haiti">Haiti</option><option value="Holy See">Holy See</option><option value="Honduras">Honduras</option><option value="Hong Kong">Hong Kong</option><option value="Hungary">Hungary</option><option value="Iceland">Iceland</option><option value="India">India</option><option value="Indonesia">Indonesia</option><option value="Iran (Islamic Republic Of)">Iran (Islamic Republic Of)</option><option value="Iraq">Iraq</option><option value="Ireland">Ireland</option><option value="Israel">Israel</option><option value="Italy">Italy</option><option value="Ivory Coast">Ivory Coast</option><option value="Jamaica">Jamaica</option><option value="Japan">Japan</option><option value="Jordan">Jordan</option><option value="Kazakhstan">Kazakhstan</option><option value="Kenya">Kenya</option><option value="Kiribati">Kiribati</option><option value="Korea, Democratic People's Rep. (North Korea)">Korea, Democratic People's Rep. (North Korea)</option><option value="Korea, Republic of (South Korea)">Korea, Republic of (South Korea)</option><option value="Kosovo">Kosovo</option><option value="Kuwait">Kuwait</option><option value="KKyrgyzstan">Kyrgyzstanc</option><option value="Lao">Lao</option><option value="Latvia">Latvia</option><option value="Lebanon">Lebanon</option><option value="Lesotho">Lesotho</option><option value="Liberia">Liberia</option><option value="Libya">Libya</option><option value="Liechtenstein">Liechtenstein</option><option value="Lithuania">Lithuania</option><option value="Luxembourg">Luxembourg</option><option value="Macau">Macau</option><option value="Madagascar">Madagascar</option><option value="Malawi">Malawi</option><option value="Malaysia">Malaysia</option><option value="Maldives">Maldives</option><option value="Mali">Mali</option><option value="Malta">Malta</option><option value="Marshall Islands">Marshall Islands</option><option value="Martinique">Martinique</option><option value="Mauritania">Mauritania</option><option value="Mauritius">Mauritius</option><option value="Mayotte">Mayotte</option><option value="Mexico">Mexico</option><option value="Micronesia, Federated States Of">Micronesia, Federated States Of</option><option value="Moldova, Republic Of">Moldova, Republic Of</option><option value="Monaco">Monaco</option><option value="Mongolia">Mongolia</option><option value="Montenegro">Montenegro</option><option value="Montserrat">Montserrat</option><option value="Morocco">Morocco</option><option value="Mozambique">Mozambique</option><option value="Myanmar">Myanmar</option><option value="Namibia">Namibia</option><option value="Nauru">Nauru</option><option value="Nepal">Nepal</option><option value="Netherlands">Netherlands</option><option value="Netherlands Antilles">Netherlands Antilles</option><option value="New Caledonia">New Caledonia</option><option value="New Zealand">New Zealand</option><option value="Nicaragua">Nicaragua</option><option value="Niger">Niger</option><option value="Nigeria">Nigeria</option><option value="Niue">Niue</option><option value="North Macedonia">North Macedonia</option><option value="Northern Mariana Islands">Northern Mariana Islands</option><option value="Norway">Norway</option><option value="Oman">Oman</option><option value="Pakistan">Pakistan</option><option value="Palau">Palau</option><option value="Palestinian territories">Palestinian territories</option><option value="Panama">Panama</option><option value="Papua New Guinea">Papua New Guinea</option><option value="Paraguay">Paraguay</option><option value="Peru">Peru</option><option value="Philippines">Philippines</option><option value="Pitcairn">Pitcairn</option><option value="Poland">Poland</option><option value="Portugal">Portugal</option><option value="Puerto Rico">Puerto Rico</option><option value="Qatar">Qatar</option><option value="Reunion Island">Reunion Island</option><option value="Romania">Romania</option><option value="Russian Federation">Russian Federation</option><option value="Rwanda">Rwanda</option><option value="Saint Kitts And Nevis">Saint Kitts And Nevis</option><option value="Saint Lucia">Saint Lucia</option><option value="Saint Vincent and the Grenadines">Saint Vincent and the Grenadines</option><option value="Samoa">Samoa</option><option value="Sao Tome and Principe">Sao Tome and Principe</option><option value="San Marino">San Marino</option><option value="Saudi Arabia">Saudi Arabia</option><option value="Senegal">Senegal</option><option value="Serbia">Serbia</option><option value="Seychelles">Seychelles</option><option value="Sierra Leone">Sierra Leone</option><option value="Singapore">Singapore</option><option value="Slovakia (Slovak Republic)">Slovakia (Slovak Republic)</option><option value="Slovenia">Slovenia</option><option value="Solomon Islands">Solomon Islands</option><option value="Somalia">Somalia</option><option value="South Africa">South Africa</option><option value="South Sudan">South Sudan</option><option value="Spain">Spain</option><option value="Sri Lanka">Sri Lanka</option><option value="Sudan">Sudan</option><option value="Suriname">Suriname</option><option value="Swaziland">Swaziland</option><option value="Sweden">Sweden</option><option value="Switzerland">Switzerland</option><option value="Syria, Syrian Arab Republic">Syria, Syrian Arab Republic</option><option value="Taiwan">Taiwan</option><option value="Tajikistan">Tajikistan</option><option value="Tanzania">Tanzania</option><option value="Thailand">Thailand</option><option value="Tibet">Tibet</option><option value="Timor-Leste (East Timor)">Timor-Leste (East Timor)</option><option value="Togo">Togo</option><option value="Tokelau">Tokelau</option><option value="Tonga">Tonga</option><option value="Trinidad and Tobago">Trinidad And Tobago</option><option value="Tunisia">Tunisia</option><option value="Turkey">Turkey</option><option value="Turkmenistan">Turkmenistan</option><option value="Turks and Caicos Islands">Turks And Caicos Islands</option><option value="Tuvalu">Tuvalu</option><option value="Uganda">Uganda</option><option value="Ukraine">Ukraine</option><option value="United Arab Emirates">United Arab Emirates</option><option value="United Kingdom">United Kingdom</option><option value="United States">United States</option><option value="Uruguay">Uruguay</option><option value="Uzbekistan">Uzbekistan</option><option value="Vanuatu">Vanuatu</option><option value="Vatican City State (Holy See)">Vatican City State (Holy See)</option><option value="Venezuela">Venezuela</option><option value="Vietnam">Vietnam</option><option value="Virgin Islands (British)">Virgin Islands (British)</option><option value="Virgin Islands (U.S.)">Virgin Islands (U.S.)</option><option value="Wallis And Futuna Islands">Wallis And Futuna Islands</option><option value="Western Sahara">Western Sahara</option><option value="Yemen">Yemen</option><option value="Zambia">Zambia</option><option value="Zimbabwe">Zimbabwe</option>                  </select>
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6 swiftper1" style="display:none">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="SWIFT Transfer Fee" readonly="" value="SWIFT FEE :" name="swift_transfer[]" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6 sepaper1" style="display:none">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="SEPA Transfer Fee" value="SEPA FEE:" name="sepa_transfer[]" readonly="" class="form-control">
  //                   </div>
  //                 </div>
  //                 <div class="col-lg-6">
  //                   <div class="form-group">
  //                     <input type="text" placeholder="Reference no:" name="reference_no[]" class="form-control">
  //                   </div>
  //                 </div>
  //               </div>
  //             </div>
  //           </div>
  //     </form></div>
  // </div>
  //
);
