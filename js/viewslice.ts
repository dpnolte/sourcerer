import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-12T15:41:19.973 */
export namespace ViewSliceTypes {
  export interface SliceViewAttributes extends MainTypes.ViewGroupAttributes {
  }
}
// elements
export const SliceView = (
  attributes?: ViewSliceTypes.SliceViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ViewSliceTypes.SliceViewAttributes, LayoutParamAttributes> => {
  return element('sliceView', attributes, children);
};